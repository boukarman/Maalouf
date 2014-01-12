Ext.define('ChartCause', {
	extend: 'Ext.data.Model',
	fields: ['cause', 'number']
});

Ext.apply(tn.tunisietelecom.chart.CauseChart, {

	storeChartCauseId: '',
	
	initCause: function(){
		this.fillChartCauseStore();
		this.setUpChartCause();
		this.setUpSaveChartCauseBtn();
	},
	
	setUpSiteComboBox: function(){
		var me = this;
		Ext.define('Site', {
			extend: 'Ext.data.Model',
			fields: ['siteId', 'labSite']
		});
		var siteStore = Ext.create('Ext.data.Store', {
							 model: 'Site',
							 proxy: {
								 type: 'ajax',
								 url: tn.tunisietelecom.Constants.sites,
								 reader: {
									 type: 'json',
									 root: ''
								 }
							 },
							 autoLoad: false
						 });
		if(Ext.getCmp('site-combobox-id'))
			Ext.getCmp('site-combobox-id').destroy();
		Ext.create('Ext.form.ComboBox', {
			fieldLabel: 'Site',
			store: siteStore,
			emptyText : '-- choisir un site --',
			queryMode: 'local',
			valueField: 'siteId',
			renderTo: 'site-combobox',
			id: 'site-combobox-id',
			tpl: Ext.create('Ext.XTemplate',
				'<tpl for=".">',
					'<div class="x-boundlist-item">{labSite}</div>',
				'</tpl>'
			),
			// template for the content inside text field
			displayTpl: Ext.create('Ext.XTemplate',
				'<tpl for=".">',
					'{labSite}',
				'</tpl>'
			),
			listeners: {
				change: function(comboBox, newValue, oldValue, eOpts){
					if(Ext.getCmp('local-combobox-id'))
						Ext.getCmp('local-combobox-id').destroy();
					if(newValue != -1){
						me.setUpLocalComboBox(newValue);
					}
					me.setUpStartDate();
					me.setUpEndDate();
					me.setUpShowChartBtn();
				}
			}
		});
		siteStore.load();
	},
	
	setUpLocalComboBox: function(selectedSite){
			Ext.define('Local', {
				extend: 'Ext.data.Model',
				fields: ['idLocal', 'labLocal']
			});
			var localStore = Ext.create('Ext.data.Store', {
							 model: 'Local',
							 proxy: {
								 type: 'ajax',
								 url: tn.tunisietelecom.Constants.locals,
								 extraParams: {
									selectedSite: selectedSite
								 },
								 reader: {
									 type: 'json',
									 root: ''
								 }
							 },
							 autoLoad: false
						 });
			Ext.create('Ext.form.ComboBox', {
				fieldLabel: 'Locale',
				store: localStore,
				queryMode: 'local',
				emptyText : '-- choisir un locale --',
				valueField: 'idLocal',
				renderTo: 'local-combobox',
				id: 'local-combobox-id',
				tpl: Ext.create('Ext.XTemplate',
					'<tpl for=".">',
						'<div class="x-boundlist-item">{labLocal}</div>',
					'</tpl>'
				),
				// template for the content inside text field
				displayTpl: Ext.create('Ext.XTemplate',
					'<tpl for=".">',
						'{labLocal}',
					'</tpl>'
				)
			});
			localStore.load();
	},
	
	fillChartCauseStore: function(){	
		var me = this, 
		startDate =  Ext.getCmp('start-date-chart-id').getValue(),
		startTime = Ext.getCmp('start-time-chart-id').getValue(),
		endDate = Ext.getCmp('end-date-chart-id').getValue(),
		endTime = Ext.getCmp('end-time-chart-id').getValue();
		startDate.setHours(startTime.getHours());
		startDate.setMinutes(startTime.getMinutes());
		endDate.setHours(endTime.getHours());
		endDate.setMinutes(endTime.getMinutes()),
		chartStore = Ext.data.StoreManager.lookup(me.storeChartCauseId);
		if(!chartStore){
			this.storeChartCauseId = this.generateID();
			 Ext.create('Ext.data.Store', {
				 model: 'ChartCause',
				 storeId: me.storeChartCauseId,
				 proxy: {
					 type: 'ajax',
					 url: tn.tunisietelecom.Constants.chartCauseElements,
					 extraParams : {
						 site: Ext.getCmp('site-combobox-id').getValue(),
						 local: Ext.getCmp('local-combobox-id').getValue(),
						 startDate: startDate,
						 endDate: endDate
					 },
					 reader: {
						 type: 'json',
						 root: ''
					 }
				 },
				 autoLoad: true
			 });
		}else {
			chartStore.load({
				 params : {
						 site: Ext.getCmp('site-combobox-id').getValue(),
						 local: Ext.getCmp('local-combobox-id').getValue(),
						 startDate: startDate,
						 endDate: endDate
					 }
				});
		}
	},
	
	setUpChartCause: function(){
		var me = this, chart = Ext.getCmp('chart-cause-id');
		if(chart){
			chart.redraw();
		}else {
		 Ext.create('Ext.chart.Chart', {
	            style: 'background:#fff',
	            animate: true,
	            shadow: true,
	            width: 600,
	            height: 400,
				renderTo: 'chart-cause',
				id: 'chart-cause-id',
	            store: Ext.data.StoreManager.lookup(me.storeChartCauseId),
	            axes: [{
	                type: 'Numeric',
	                position: 'left',
	                fields: ['number'],
	                title: 'Nombre de pannes',
	                grid: true,
	                maximum: 20,
	                minimum: 0
	            }, {
	                type: 'Category',
	                position: 'bottom',
	                fields: ['cause'],
	                title: 'Les causes'
	            }],
	            series: [{
	                type: 'column',
	                axis: 'left',
	                highlight: true,
					tips: {
					  trackMouse: true,
					  width: 200,
					  height: 28,
					  renderer: function(storeItem, item) {
						this.setTitle(storeItem.get('cause') + ': ' + storeItem.get('number') + ' fois.');
					  }
					},
					label: {
						display: 'insideEnd',
						'text-anchor': 'middle',
						field: 'data1',
						renderer: Ext.util.Format.numberRenderer('0'),
						orientation: 'vertical',
						color: '#333'
					},
	                xField: 'cause',
	                yField: 'number'
	            }]
	        });
		}
	},
		
	setUpShowChartBtn: function(){
		var me = this;
		if(Ext.getCmp('show-chart-btn-id'))
			Ext.getCmp('show-chart-btn-id').destroy();
		Ext.create('Ext.Button', {
			text: 'Charger statistique',
			renderTo: 'show-chart-btn',
			id: 'show-chart-btn-id',
			handler: function() {
				me.initCause();
			}
		});
	},
	
	setUpSaveChartCauseBtn: function(){
		if(Ext.getCmp('save-chart-btn-id'))
			Ext.getCmp('save-chart-btn-id').destroy();
		Ext.create('Ext.Button', {
			text: 'Sauvegarder statistique',
			renderTo: 'save-chart-btn',
			id: 'save-chart-btn-id',
			handler: function() {
				Ext.MessageBox.confirm('Confirmation', 'Voulez vous t\u00E9l\u00E9charger la statistique en tant que image?', function(choice){
	                if(choice == 'yes'){
	                	Ext.getCmp('chart-cause-id').save({
	                        type: 'image/jpeg'
	                    });
	                }
	            });
			}
		});
	}
}, tn.tunisietelecom.Chart);
