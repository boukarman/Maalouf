Ext.apply(tn.tunisietelecom.Piece, {
	
	pieceStoreId: '',
	
	pieceGridId: '',
	
	generateID: function(){
		return Ext.data.IdGenerator.get('uuid').generate();
	},
	
	init: function(){
		$('.piece-form form select[name=site]').change(function(e){
			$(this).parent().submit();
		});
		this.setUpSubmitListener();
	},
	
	setUpSubmitListener: function(){	
		var me = this;
		$(".piece-form form").submit(function(e){
			e.preventDefault();
			e.stopPropagation();
			var id = tn.tunisietelecom.MainView.contentPanelID,
				site = $('.piece-form select[name=site]').val();		
			if(site && site == -1)
				return;
			else if(!site){
				 var options = { 
					method:'POST',   // target element(s) to be updated with server response
					target: '.piece-form',
					success: me.showResponse
				};
				// submit the form 
				$(this).ajaxSubmit(options);
			}
			else {
				Ext.getCmp(id).getLoader().load({
					url: tn.tunisietelecom.Constants.createPiece + "?local",
					ajaxOptions: {
						method: 'GET'
					},
					params: {
						site: site ? site : ''
					}
				});
			}
		});
	},
	
	showResponse: function(response, b){
		//debugger;
	},
	
	fillPieceGrid: function(){	
		var me = this;
		Ext.define('Piece', {
			 extend: 'Ext.data.Model',
			 fields: [
				 {name: 'idPiece', 	type: 'float'},
				 {name: 'labPiece',  type: 'string'},
				 {name: 'establishementDate',  mapping: 'establishementDate'},
				 {name: 'status',  type: 'string'},
				 {name: 'mark',  type: 'string'},
				 {name: 'supplier',  type: 'string'},
				 {name: 'labSite',  type: 'string'},
				 {name: 'labLocal',  type: 'string'}
				 ]
			});
			
		var pieceStore = Ext.create('Ext.data.Store', {
			 model: 'Piece',
			 pageSize: 10, // items per page
			 proxy: {
				 type: 'ajax',
				 url: tn.tunisietelecom.Constants.consultPieceElements,
				 reader: {
					 type: 'json',
					 root: 'pieces',
					 totalProperty: 'totalCount'
				 }
			 },
			 autoLoad: true
		 });
		this.pieceGridId = this.generateID();
		Ext.create('Ext.grid.Panel', {
			title: '\u00E9quipement',
			id: me.pieceGridId,
			store: pieceStore,
			columns: [
				{ dataIndex: 'idPiece', hidden: true },
				{ text: 'Libell\u00E9', dataIndex: 'labPiece', width: 180, align: 'center'},
				{ text: 'Date mise en place', dataIndex: 'establishementDate', width: 180, align: 'center', renderer: me.renderPieceDate },
				{ text: 'Etat', dataIndex: 'status', width: 100, align: 'center'},
				{ text: 'Marque', dataIndex: 'mark', width: 100, align: 'center' },
				{ text: 'Fournisseur', dataIndex: 'supplier', width: 100, align: 'center' },
				{ text: 'Site', dataIndex: 'labSite', width: 200, align: 'center' },
				{ text: 'Locale', dataIndex: 'labLocal', width: 100, align: 'center' }
			],
			height: 400,
			width: 'auto',
			renderTo: 'grid-piece',
			dockedItems: [{
				xtype: 'toolbar',
				dock: 'top',
				items: me.getPieceGridConsultToolbarItems()
			},{
					xtype: 'pagingtoolbar',
					store: pieceStore,   // same store GridPanel is using
					dock: 'bottom',
					displayInfo: true
				}]
		});
	},
	
	getPieceGridConsultToolbarItems: function(){
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
										url: tn.tunisietelecom.Constants.deletePiece,
										params: {
											idPiece: parseInt(site[0].get('idPiece'))
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
								 msg: 'Voulez vous vraiment supprimer cet \u00E9quipement?',
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
	
	consultPiece: function(){
		this.fillPieceGrid();
	},
	
	renderPieceDate:function (value, p, r) {
		return Ext.String.format('{0}', Ext.Date.dateFormat(new Date(value), 'd/m/Y h:i a'), r.get('establishementDate'));
	}
});