Ext.apply(tn.tunisietelecom.Site, {
	
	siteStoreId: '',
	
	siteGridId: '',
	
	generateID: function(){
		return Ext.data.IdGenerator.get('uuid').generate();
	},
	
	setUpSubmitListener: function(){
		$(".site-form form").submit(function(e){
			e.preventDefault();
			e.stopPropagation();
			var id = tn.tunisietelecom.MainView.contentPanelID,
			labSite = $('.site-form input[name=labSite]').val(),
			address = $('.site-form input[name="address"]').val();

			Ext.getCmp(id).getLoader().load({
				url: tn.tunisietelecom.Constants.createSite,
				params: {
					labSite: labSite ? labSite: '',
					address: address ? address : ''
				}
			});
		});
	},
	
	fillSiteGrid: function(){	
		var me = this;
		Ext.define('Site', {
			 extend: 'Ext.data.Model',
			 fields: [
				 {name: 'siteId', 	type: 'float'},
				 {name: 'labSite',  type: 'string'},
				 {name: 'address',  type: 'string'}
				 ]
			});
		var siteStore = Ext.create('Ext.data.Store', {
			 model: 'Site',
			 pageSize: 10, // items per page
			 proxy: {
				 type: 'ajax',
				 url: tn.tunisietelecom.Constants.consultSiteElements,
				 reader: {
					 type: 'json',
					 root: 'sites',
					 totalProperty: 'totalCount'
				 }
			 },
			 autoLoad: true
		 });
		this.siteGridId = this.generateID();
		Ext.create('Ext.grid.Panel', {
			title: 'Sites',
			id: me.siteGridId,
			store: siteStore,
			columns: [
				{ dataIndex: 'siteId', hidden: true },
				{ text: 'Libell\u00E9 site', dataIndex: 'labSite', width: 200, align: 'center'},
				{ text: 'Addresse', dataIndex: 'address', width: 200, align: 'center' }
			],
			height: 400,
			width: 'auto',
			renderTo: 'grid-site',
			dockedItems: [{
				xtype: 'toolbar',
				dock: 'top',
				items: me.getSiteGridConsultToolbarItems()
			},{
					xtype: 'pagingtoolbar',
					store: siteStore,   // same store GridPanel is using
					dock: 'bottom',
					displayInfo: true
				}]
		});
	},
	
	getSiteGridConsultToolbarItems: function(){
		var me= this, items = [];
		if(tn.tunisietelecom.Role.canDeleteAndModify()){
			items.push({
					text: 'Modifier'
				});
			items.push({
					text: 'Supprimer',
					handler: function(){
						var containerGrid = this.up('grid'), site = containerGrid.getSelectionModel().getSelection();
						if(site.length > 0){
							var deleteFn = function(btn) {
								if (btn == 'yes'){
									Ext.Ajax.request({
										url: tn.tunisietelecom.Constants.deleteSite,
										params: {
											siteId: parseInt(site[0].get('siteId'))
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
								 msg: 'Voulez vous vraiment supprimer ce site?',
								 fn: deleteFn,
								 buttons: Ext.Msg.YESNOCANCEL,
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
	
	consultSite: function(){
		this.fillSiteGrid();
	}
	
});