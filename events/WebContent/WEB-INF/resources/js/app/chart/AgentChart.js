Ext.define('ChartAgent', {
	extend: 'Ext.data.Model',
	fields: ['fullName', 'number']
});

Ext.apply(tn.tunisietelecom.chart.AgentChart, {

	storeChartAgentId: '',
	
	initAgent: function(){
		this.fillChartAgentStore();
		this.setUpChartAgent();
		this.setUpSaveChartAgentBtn();
	},
	
	fillChartAgentStore: function(){	
		var me = this, 
		startDate =  Ext.getCmp('start-date-chart-id').getValue(),
		startTime = Ext.getCmp('start-time-chart-id').getValue(),
		endDate = Ext.getCmp('end-date-chart-id').getValue(),
		endTime = Ext.getCmp('end-time-chart-id').getValue();
		startDate.setHours(startTime.getHours());
		startDate.setMinutes(startTime.getMinutes());
		endDate.setHours(endTime.getHours());
		endDate.setMinutes(endTime.getMinutes()),
		chartStore = Ext.data.StoreManager.lookup(me.storeChartAgentId);
		if(!chartStore){
			this.storeChartAgentId = this.generateID();
			 Ext.create('Ext.data.Store', {
				 model: 'ChartAgent',
				 storeId: me.storeChartAgentId,
				 proxy: {
					 type: 'ajax',
					 url: tn.tunisietelecom.Constants.chartAgentElements,
					 extraParams : {
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
						 startDate: startDate,
						 endDate: endDate
					 }
				});
		}
	},
	
	setUpChartAgent: function(){
		var me = this, chart = Ext.getCmp('chart-agent-id');
		if(chart){
			chart.redraw();
		}else {
		 Ext.create('Ext.chart.Chart', {
	            style: 'background:#fff',
	            animate: true,
	            shadow: true,
	            width: 600,
	            height: 400,
				renderTo: 'chart-agent',
				id: 'chart-agent-id',
	            store: Ext.data.StoreManager.lookup(me.storeChartAgentId),
	            axes: [{
	                type: 'Numeric',
	                position: 'left',
	                fields: ['number'],
	                title: 'Nombre d\'intervention',
	                grid: true,
	                maximum: 10,
	                minimum: 0
	            }, {
	                type: 'Category',
	                position: 'bottom',
	                fields: ['fullName'],
	                title: 'Les agents'
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
						this.setTitle(storeItem.get('fullName') + ': ' + storeItem.get('number') + ' intervention(s).');
					  }
					},
					label: {
						display: 'insideEnd',
						'text-anchor': 'middle',
						field: 'fullName',
						renderer: Ext.util.Format.numberRenderer('0'),
						orientation: 'vertical',
						color: '#333'
					},
	                xField: 'fullName',
	                yField: 'number'
	            }]
	        });
		}
	},
	
	setUpShowChartAgentBtn: function(){
		var me = this;
		if(Ext.getCmp('show-chart-btn-id'))
			Ext.getCmp('show-chart-btn-id').destroy();
		Ext.create('Ext.Button', {
			text: 'Charger statistique',
			renderTo: 'show-chart-btn',
			id: 'show-chart-btn-id',
			handler: function() {
				me.initAgent();
			}
		});
	},
	
	setUpSaveChartAgentBtn: function(){
		if(Ext.getCmp('save-chart-btn-id'))
			Ext.getCmp('save-chart-btn-id').destroy();
		Ext.create('Ext.Button', {
			text: 'Sauvegarder statistique',
			renderTo: 'save-chart-btn',
			id: 'save-chart-btn-id',
			handler: function() {
				Ext.MessageBox.confirm('Confirmation', 'Voulez vous t\u00E9l\u00E9charger la statistique en tant que image?', function(choice){
	                if(choice == 'yes'){
	                	Ext.getCmp('chart-agent-id').save({
	                        type: 'image/jpeg'
	                    });
	                }
	            });
			}
		});
	}
	
}, tn.tunisietelecom.Chart);
