Ext.define('ChartPerformance', {
	extend: 'Ext.data.Model',
	fields: ['criteria', 'number', 'events']
});

 Ext.define('EventSeries', {
     extend: 'Ext.data.Model',
     fields: ['site', 'local', 'piece', 'cause', 'description', 'workingInUser', 'startDate', 'finishedDate']
});

Ext.apply(tn.tunisietelecom.chart.PerformanceChart, {
	storeChartPerformanceId: '',
	
	initPerformance: function(){
		this.fillChartPerformanceStore();
		this.setUpChartPerformance();
		this.setUpSaveChartPerformanceBtn();
	},
	
	fillChartPerformanceStore: function(){	
		var me = this, 
		startDate =  Ext.getCmp('start-date-chart-id').getValue(),
		startTime = Ext.getCmp('start-time-chart-id').getValue(),
		endDate = Ext.getCmp('end-date-chart-id').getValue(),
		endTime = Ext.getCmp('end-time-chart-id').getValue();
		startDate.setHours(startTime.getHours());
		startDate.setMinutes(startTime.getMinutes());
		endDate.setHours(endTime.getHours());
		endDate.setMinutes(endTime.getMinutes()),
		chartStore = Ext.data.StoreManager.lookup(me.storeChartPerformanceId);
		if(!chartStore){
			this.storeChartPerformanceId = this.generateID();
			 Ext.create('Ext.data.Store', {
				 model: 'ChartPerformance',
				 storeId: me.storeChartPerformanceId,
				 proxy: {
					 type: 'ajax',
					 url: tn.tunisietelecom.Constants.chartPerformanceElements,
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
	
	renderStartDate:function (value, p, r) {
		return Ext.String.format('{0}', Ext.Date.dateFormat(new Date(value), 'd/m/Y h:i a'), r.get('startDate'));
	},
	
	renderFinishedDate:function (value, p, r) {
		return Ext.String.format('{0}', Ext.Date.dateFormat(new Date(value), 'd/m/Y h:i a'), r.get('finishedDate'));
	},
	
	setUpChartPerformance: function(){
		var me = this, chart = Ext.getCmp('chart-performance-id');
		if(chart){
			chart.redraw();
		}else {
			var chartStore = Ext.data.StoreManager.lookup(me.storeChartPerformanceId);
			Ext.create('Ext.chart.Chart', {
				animate: true,
				width: 500,
			    height: 350,
				renderTo: 'chart-performance',
				id: 'chart-performance-id',
				store: chartStore,
				shadow: true,
				legend: {
					position: 'right'
				},
				insetPadding: 60,
				theme: 'Base:gradients',
				series: [{
					type: 'pie',
					field: 'number',
					showInLegend: true,
					donut: false,
					tips: {
					  trackMouse: true,
					  width: 140,
					  height: 28,
					  renderer: function(storeItem, item) {
						//calculate percentage.
						var total = 0;
						chartStore.each(function(rec) {
							total += rec.get('number');
						});
						this.setTitle(storeItem.get('criteria') + ': ' + Math.round(storeItem.get('number') / total * 100) + '%');
					  }
					},
					highlight: {
					  segment: {
						margin: 20
					  }
					},
					label: {
						field: 'criteria',
						display: 'rotate',
						contrast: true,
						font: '18px Arial'
					},
			        listeners: {
		                itemclick: function(serie) {
						var storeSerie = Ext.create('Ext.data.Store', {
							 model: 'EventSeries',
							 proxy: {
								 type: 'ajax',
								 url: tn.tunisietelecom.Constants.chartPerformanceSeriesElements,
								 extraParams: {
									events: serie.storeItem.get('events')
								 },
								 reader: {
									 type: 'json',
									 root: ''
								 }
							 },
							 autoLoad: true
						 });
						 // ['site', 'local', 'piece', 'cause', 'description', 'workingInUser', 'startDate', 'finishedDate']
						var serieGrid = Ext.create('Ext.grid.Panel', {
											title: 'Events',
											store: storeSerie,
											columns: [
												{ text: 'Site', dataIndex: 'site', align: 'center' },
												{ text: 'Locale', dataIndex: 'local', align: 'center' },
												{ text: 'Pi\u00E8ce', dataIndex: 'piece', align: 'center' },
												{ text: 'Libell\u00E9 alarme', dataIndex: 'cause', align: 'center' },
												{ text: 'Description', dataIndex: 'description', align: 'center' },
												{ text: 'R\u00E9parateur', dataIndex: 'workingInUser', align: 'center' },
												{ text: 'Date d\u00E9but', dataIndex: 'startDate', width: 180, align: 'center', renderer: me.renderStartDate},
												{ text: 'Date fin', dataIndex: 'finishedDate', width: 180, align: 'center', renderer: me.renderFinishedDate}
											],
											height: 400,
											width: 'auto'
										});
						//chartPerformanceSeriesElements
						  Ext.create('Ext.window.Window', {
								height: 400,
								width: 850,
								modal: true,
								layout: 'fit',
								items: [serieGrid]
							}).show();
		                }
					}
				}]
			});
		}
	},
	setUpShowChartPerformanceBtn: function(){
		var me = this;
		if(Ext.getCmp('show-chart-btn-id'))
			Ext.getCmp('show-chart-btn-id').destroy();
		Ext.create('Ext.Button', {
			text: 'Charger statistique',
			renderTo: 'show-chart-btn',
			id: 'show-chart-btn-id',
			handler: function() {
				me.initPerformance();
			}
		});
	},
	
	setUpSaveChartPerformanceBtn: function(){
		if(Ext.getCmp('save-chart-btn-id'))
			Ext.getCmp('save-chart-btn-id').destroy();
		Ext.create('Ext.Button', {
			text: 'Sauvegarder statistique',
			renderTo: 'save-chart-btn',
			id: 'save-chart-btn-id',
			handler: function() {
				Ext.MessageBox.confirm('Confirmation', 'Voulez vous t\u00E9l\u00E9charger la statistique en tant que image?', function(choice){
	                if(choice == 'yes'){
	                	Ext.getCmp('chart-performance-id').save({
	                        type: 'image/jpeg'
	                    });
	                }
	            });
			}
		});
	}
}, tn.tunisietelecom.Chart);
