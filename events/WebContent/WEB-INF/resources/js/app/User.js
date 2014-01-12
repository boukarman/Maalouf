Ext.apply(tn.tunisietelecom.User, {
	
	currentUser: -1,
	
	userStoreId: '',
	
	userGridId: '',
	
	generateID: function(){
		return Ext.data.IdGenerator.get('uuid').generate();
	},
	
	init: function(){
		this.subcontractorTypeChangeHandler();
		this.setUpSubmitListener();
	},
	
	subcontractorTypeChangeHandler: function(){
		$('.user-form form select[name=idRole]').change(function(e){
			var parent = $('.user-form form select[name=subcontractorTypeId]').parent();
			if($(this).val() == tn.tunisietelecom.Role.SUBCONTRACTOR){
				parent.removeClass("hidden");
			}
			else if ($(this).val() != tn.tunisietelecom.Role.SUBCONTRACTOR && !parent.hasClass("hidden")){
				parent.addClass("hidden");
			}
		});
	},
	
	showResponse: function(response, b){
		//debugger;
	},
	setUpSubmitListener: function(){
		var me = this;
		$(".user-form form").submit(function(e){
			e.preventDefault();
			e.stopPropagation();
			 var options = { 
				method:'POST',   // target element(s) to be updated with server response
				target: '.user-form',
				success: me.showResponse
			}
			// submit the form 
			$(this).ajaxSubmit(options);
		});
	},
	
	fillUserGrid: function(){	
		var me = this;
		Ext.define('User', {
			 extend: 'Ext.data.Model',
			 fields: [
				 {name: 'userId', 	type: 'float'},
				 {name: 'login',  type: 'string'},
				 {name: 'password',  type: 'string'},
				 {name: 'lastName',  type: 'string'},
				 {name: 'firstName',  type: 'string'},
				 {name: 'address',  type: 'string'},
				 {name: 'email',  type: 'string'},
				 {name: 'telNumber',  type: 'string'},
				 {name: 'formation',  type: 'string'},
				 {name: 'grade',  type: 'string'},
				 {name: 'role',  type: 'string'}
				 ]
			});
		var userStore = Ext.create('Ext.data.Store', {
			 model: 'User',
			 pageSize: 10, // items per page
			 proxy: {
				 type: 'ajax',
				 url: tn.tunisietelecom.Constants.consultUserElements,
				 reader: {
					 type: 'json',
					 root: 'users',
					 totalProperty: 'totalCount'
				 }
			 },
			 autoLoad: true
		 });
		this.userGridId = this.generateID();
		Ext.create('Ext.grid.Panel', {
			title: 'Agents',
			id: me.userGridId,
			store: userStore,
			columns: [
				{ dataIndex: 'userId', hidden: true },
				{ text: 'Login', dataIndex: 'login', width: 100, align: 'center'},
				{ text: 'Mot de passe', dataIndex: 'password', width: 100, align: 'center' },
				{ text: 'Nom', dataIndex: 'lastName', width: 120, align: 'center' },
				{ text: 'Pr\u00E9nom', dataIndex: 'firstName', width: 120, align: 'center' },
				{ text: 'Addresse', dataIndex: 'address', width: 120, align: 'center' },
				{ text: 'E-mail', dataIndex: 'email', width: 140, align: 'center' },
				{ text: 'Tel. Num.', dataIndex: 'telNumber', width: 100, align: 'center' },
				{ text: 'Formation', dataIndex: 'formation', width: 100, align: 'center' },
				{ text: 'Grade', dataIndex: 'grade', width: 100, align: 'center' },
				{ text: 'Role', dataIndex: 'role', width: 180, align: 'center' }
			],
			height: 400,
			width: 'auto',
			renderTo: 'grid-user',
			dockedItems: [{
				xtype: 'toolbar',
				dock: 'top',
				items: me.getUserGridConsultToolbarItems()
			},{
					xtype: 'pagingtoolbar',
					store: userStore,   // same store GridPanel is using
					dock: 'bottom',
					displayInfo: true
				}]
		});
	},
	
	getUserGridConsultToolbarItems: function(){
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
										url: tn.tunisietelecom.Constants.deleteUser,
										params: {
											userId: parseInt(site[0].get('userId'))
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
								 msg: 'Voulez vous vraiment supprimer cet agent?',
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
	
	consultUser: function(){
		this.fillUserGrid();
	}
});