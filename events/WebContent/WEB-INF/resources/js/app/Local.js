Ext.apply(tn.tunisietelecom.Local, {
	
	localStoreId: '',
	
	localGridId: '',
	
	generateID: function(){
		return Ext.data.IdGenerator.get('uuid').generate();
	},
	
	setUpSubmitListener: function(){
		$(".local-form form").submit(function(e){
			e.preventDefault();
			e.stopPropagation();
			var id = tn.tunisietelecom.MainView.contentPanelID,
			labLocal = $('.local-form input[name=labLocal]').val(),
			siteId = $('.local-form select[name="site"]').val(),
			position = $('.local-form input[name="position"]').val();

			Ext.getCmp(id).getLoader().load({
				url: tn.tunisietelecom.Constants.createLocal,
				params: {
					labLocal: labLocal ? labLocal: '',
					siteId: siteId ? siteId : '',
					position: position ? position : ''
				}
			});
		});
	},

	fillLocalGrid: function(){		
		var me = this;
		Ext.define('Local', {
			 extend: 'Ext.data.Model',
			 fields: [
			         {name: 'idLocal',  mapping:'idLocal'},
			         {name: 'labLocal',  mapping:'labLocal'},
					 {name: 'labSite',  mapping:'labSite'},
					 {name: 'position', mapping:'position'},
					 {name: 'siteId', 	mapping:'siteId'}
				 ]
			});
		var localStore = Ext.create('Ext.data.Store', {
				 model: 'Local',
				 pageSize: 10, // items per page
				 proxy: {
					 type: 'ajax',
					 url: tn.tunisietelecom.Constants.consultLocalElements,
					 reader: {
						 type: 'json',
						 root: 'locals',
						 totalProperty: 'totalCount'
					 }
				 },
				 autoLoad: true
			 });
		this.localGridId = this.generateID();
		Ext.create('Ext.grid.Panel', {
			title: 'Locaux',
			id: me.localGridId,
			store: localStore,
			columns: [
				{ dataIndex: 'idLocal', hidden: true },
				{ text: 'Libell\u00E9 locale', dataIndex: 'labLocal', width: 120, align: 'center' },
				{ text: 'Position', dataIndex: 'position', align: 'center', width: 200 },
				{ text: 'Libell\u00E9 site', dataIndex: 'labSite', align: 'center', width: 200 }
			],
			height: 400,
			width: 'auto',
			renderTo: 'grid-local',
			dockedItems: [{
				xtype: 'toolbar',
				dock: 'top',
				items: me.getLocalGridConsultToolbarItems()
			},{
					xtype: 'pagingtoolbar',
					store: localStore,   // same store GridPanel is using
					dock: 'bottom',
					displayInfo: true
				}]
		});
	},
	
	getLocalGridConsultToolbarItems: function(){
		var me = this, items = [];
		if(tn.tunisietelecom.Role.canDeleteAndModify()){
			items.push({
					text: 'Modifier'
				});
			items.push({
					text: 'Supprimer',
					handler: function(){
						var containerGrid = this.up('grid'), local = containerGrid.getSelectionModel().getSelection();
						if(local.length > 0){
							var deleteFn = function(btn) {
								if (btn == 'yes'){
									Ext.Ajax.request({
										url: tn.tunisietelecom.Constants.deleteLocal,
										params: {
											idLocal: parseInt(local[0].get('idLocal'))
										},
										success: function(response, opts) {
											Ext.Msg.alert('Succ\u00E8s', 'Suppression avec succ\u00E8s.');
											containerGrid.getStore().load();
										},
										failure: function(response, opts) {
											console.log('server-side failure with status code ' + response.status);
										}
									});
								}
							};
							Ext.Msg.show({
								 title:'Suppression',
								 msg: 'Voulez vous vraiment supprimer ce locale?',
								 fn: deleteFn,
								 buttons: Ext.Msg.YESNO,
								 icon: Ext.Msg.QUESTION
							});
							
						}else {
							Ext.Msg.alert('Erreur', 'Vous devez s\u00E9lectionner une ligne.');
						}
					}
				});
		}
		return items;
	},
	
	consultLocal: function(){
		this.fillLocalGrid();
	}

});