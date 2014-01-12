/*Ext.define('Ext.BorderBoxFix', {
    override: 'Ext.AbstractComponent',


    initStyles: function(targetEl) {
        this.callParent(arguments);


        if (Ext.isBorderBox && !this.ownerCt) {
            targetEl.addCls(Ext.baseCSSPrefix + 'border-box');
        }
    }
});


Ext.onReady(function() {
    Ext.fly(document.documentElement).removeCls(Ext.baseCSSPrefix + 'border-box');
});*/

Ext.apply(tn.tunisietelecom.MainView, {
	
	contentPanelID: '',
	
	generateID: function(){
		return Ext.data.IdGenerator.get('uuid').generate();
	},
	
	init: function(){
		 var me = this;
		 me.contentPanelID = me.generateID();
		 var panel = Ext.create('Ext.panel.Panel', {
			    bodyPadding: 5,  // Don't want content to crunch against the borders
			    width: 'auto',
				height:580,
				id: me.generateID(),
			    title: $msg['event.main.panel.title'],
			    layout: 'border',
			    items: [{
			        xtype: 'panel',
			        title: $msg['event.main.panel.menu.title'],
			        width: 220,
					collapsible: true,
			        region:'west',
			        layout: { 
			            // layout-specific configs go here
			            type: 'accordion',
			            titleCollapse: false,
			            animate: true,
			            activeOnTop: true
			        },
			        items: me.getMenuItems()
			    }, {
			        xtype: 'panel',
					id: me.contentPanelID,
			        title: $msg['event.main.panel.content.title'],
					autoScroll: true,
			        region: 'center',
			        loader : {
						url : '',
						renderer : 'html',
						autoLoad : false,
						scripts : true,
						loadMask : true
					}
			    }],
			    renderTo: 'main-view-id'
			});
		 panel.doLayout();
	},
	
	getMenuItems: function(){
		var me = this, items = [{
			            title: $msg['event.main.panel.menu.item.event.label'],
			            items: [{ 
			            	width: 210,
			            	height: 90,
			                floating: false,
							xtype: 'menu', 
							items:[
								{text : $msg['event.main.panel.menu.item.event.create.label'],
								 handler: function(e, i){
									Ext.getCmp(me.contentPanelID).getLoader().load({url: tn.tunisietelecom.Constants.createEvent});
								}},
			                    {text : $msg['event.main.panel.menu.item.event.consult.label'],
								handler: function(e, i){
									Ext.getCmp(me.contentPanelID).getLoader().load({url: tn.tunisietelecom.Constants.consultEvent});
								}},
			            		{text : $msg['event.main.panel.menu.item.event.search.label'],
								handler: function(e, i){
									Ext.getCmp(me.contentPanelID).getLoader().load({url: tn.tunisietelecom.Constants.searchEvent});
								}}]
						}]
			        }];
			if(tn.tunisietelecom.Role.isAdministrator() || tn.tunisietelecom.Role.isEssAgent()){
				items.push({
		            title: $msg['event.main.panel.menu.item.site.label'],
		            items: [{ 
		            	width: 210,
		            	height: 90,
		                floating: false,
						xtype: 'menu', 
						items:[
							{text : $msg['event.main.panel.menu.item.site.create.label'],
							 handler: function(e, i){
								Ext.getCmp(me.contentPanelID).getLoader().load({url: tn.tunisietelecom.Constants.createSite});
							}},
		                    {text : $msg['event.main.panel.menu.item.site.consult.label'],
							handler: function(e, i){
								Ext.getCmp(me.contentPanelID).getLoader().load({url: tn.tunisietelecom.Constants.consultSite});
							}}]
					}]
				});
				items.push({				
		            title: $msg['event.main.panel.menu.item.local.label'],
		            items: [{ 
		            	width: 210,
		            	height: 90,
		                floating: false,
						xtype: 'menu', 
						items:[
							{text : $msg['event.main.panel.menu.item.local.create.label'],
							 handler: function(e, i){
								Ext.getCmp(me.contentPanelID).getLoader().load({url: tn.tunisietelecom.Constants.createLocal});
							}},
		                    {text : $msg['event.main.panel.menu.item.local.consult.label'],
							handler: function(e, i){
								Ext.getCmp(me.contentPanelID).getLoader().load({url: tn.tunisietelecom.Constants.consultLocal});
							}}]
					}]
				});
				items.push({
		            title: $msg['event.main.panel.menu.item.piece.label'],
		            items: [{ 
		            	width: 210,
		            	height: 90,
		                floating: false,
						xtype: 'menu', 
						items:[
							{text : $msg['event.main.panel.menu.item.piece.create.label'],
							 handler: function(e, i){
								Ext.getCmp(me.contentPanelID).getLoader().load({url: tn.tunisietelecom.Constants.createPiece});
							}},
		                    {text : $msg['event.main.panel.menu.item.piece.consult.label'],
							handler: function(e, i){
								Ext.getCmp(me.contentPanelID).getLoader().load({url: tn.tunisietelecom.Constants.consultPiece});
							}}]
					}]
		        });
		        items.push({
		            title: $msg['event.main.panel.menu.item.user.label'],
		            items: [{ 
		            	width: 210,
		            	height: 90,
		                floating: false,
						xtype: 'menu', 
						items:[
							{text : $msg['event.main.panel.menu.item.user.create.label'],
							 handler: function(e, i){
								Ext.getCmp(me.contentPanelID).getLoader().load({url: tn.tunisietelecom.Constants.createUser});
							}},
		                    {text : $msg['event.main.panel.menu.item.user.consult.label'],
							handler: function(e, i){
								Ext.getCmp(me.contentPanelID).getLoader().load({url: tn.tunisietelecom.Constants.consultUser});
							}}]
					}]
		        });
			}
			if(!tn.tunisietelecom.Role.isSubcontractor()){
				items.push({
			            title: $msg['event.main.panel.menu.item.chart.label'],
			            items: [{ 
			            	width: 210,
			            	height: 160,
			                floating: false,
							xtype: 'menu', 
							items:me.getChartMenuItems()
						}]
			        });
			}
		return items;
	},
	
	getChartMenuItems: function(){
		var me = this, items = [];
		
		if(tn.tunisietelecom.Role.isAdministrator() || tn.tunisietelecom.Role.isEESAgent()){
			items.push({text : $msg['event.main.panel.menu.item.chart.cause.label'],
			 handler: function(e, i){
				Ext.getCmp(me.contentPanelID).getLoader().load({url: tn.tunisietelecom.Constants.chartCause});
			}});
			items.push({text : $msg['event.main.panel.menu.item.chart.piece.label'],
			handler: function(e, i){
				Ext.getCmp(me.contentPanelID).getLoader().load({url: tn.tunisietelecom.Constants.chartPiece});
			}});
			items.push({text : $msg['event.main.panel.menu.item.chart.site.label'],
			handler: function(e, i){
				Ext.getCmp(me.contentPanelID).getLoader().load({url: tn.tunisietelecom.Constants.chartSite});
			}});
			items.push({text : $msg['event.main.panel.menu.item.chart.local.label'],
			handler: function(e, i){
				Ext.getCmp(me.contentPanelID).getLoader().load({url: tn.tunisietelecom.Constants.chartLocal});
			}});
		}
		
		if(tn.tunisietelecom.Role.isAdministrator() || tn.tunisietelecom.Role.isBossUnit()){
			items.push({text : $msg['event.main.panel.menu.item.chart.agent.label'],
				handler: function(e, i){
					Ext.getCmp(me.contentPanelID).getLoader().load({url: tn.tunisietelecom.Constants.chartAgent});
			}});
					
			items.push({text : $msg['event.main.panel.menu.item.chart.performance.label'],
				handler: function(e, i){
					Ext.getCmp(me.contentPanelID).getLoader().load({url: tn.tunisietelecom.Constants.chartPerformance});
			}});
		}
		
		return items;
	}
});