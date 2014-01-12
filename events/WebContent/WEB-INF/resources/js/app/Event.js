 // Set up a model to use in our Store
 Ext.define('Event', {
     extend: 'Ext.data.Model',
     fields: [
         {name: 'idEvent', 		 type: 'float'},
		 {name: 'site',  		 type: 'string'},
		 {name: 'local',  		 type: 'string'},
		 {name: 'piece',  		 type: 'string'},
         {name: 'cause',  		 type: 'string'},
		 {name: 'description',   type: 'string'},
         {name: 'responsible',   type: 'string'},
         {name: 'date',     	 mapping: 'date'},
		 {name: 'status',     	 type: 'int'},
		 {name: 'ownStatus',     type: 'int'},
		 {name: 'workingInUserId', type: 'int'}
     ]
 });
 
Ext.define('User', {
 extend: 'Ext.data.Model',
 fields: [
	 {name: 'userId', 		type: 'float'},
	 {name: 'fullName',  	type: 'string'},
	 {name: 'role',  		type: 'string'},
	 {name: 'readEventDate',  mapping: 'readEventDate'},
	 {name: 'startEventDate', mapping: 'startEventDate'}
	 ]
});
 
Ext.apply(tn.tunisietelecom.Event,{
	
	eventStoreId: '',
	eventGridId: '',
	
	init: function(){
		$('.event-form form select[name=site]').change(function(e){
			$(this).parent().submit();
		});
		
		$('.event-form form select[name=local]').change(function(e){
			$(this).parent().submit();
		});
	},
	
	setUpSubmitListener: function(){
		$(".event-form form").submit(function(e){
			e.preventDefault();
			e.stopPropagation();
			var id = tn.tunisietelecom.MainView.contentPanelID,
			site = $('.event-form select[name=site]').val(),
			siteHidden = $('.event-form input[name="site.siteId"]').val(),
			local = $('.event-form select[name=local]').val(),
			localHidden = $('.event-form input[name="local.idLocal"]').val(),
			piece = $('.event-form select[name=piece]').val(),
			cause = $('.event-form select[name=cause]').val(),
			responsible = $('.event-form select[name=responsible]').val(),
			subcontractorType = $('.event-form select[name=subcontractorType]').val(),
			eventDate = $('.event-form input[name=eventDate]').val(),
			description= $('.event-form textarea[name=description]').val();
			if(site == -1 || local == -1)
				return;
			Ext.getCmp(id).getLoader().load({
				url: tn.tunisietelecom.Constants.createEvent,
				params: {
					site: site ? site: siteHidden ? siteHidden : '',
					local: local ? local : localHidden ? localHidden : '',
					piece: piece ? piece : '',
					cause: cause ? cause : '',
					responsible: responsible ? responsible : '',
					subcontractorType: subcontractorType ? subcontractorType : '',
					eventDate: eventDate ? eventDate : '',
					description: description ? description : ''
				}
			});
		});
	},
	
	setUpCreateDate: function(){
		if(Ext.getCmp('eventDate-id'))
			Ext.getCmp('eventDate-id').destroy();
		Ext.create('Ext.form.field.Date',{
				anchor: '100%',
				fieldLabel: 'De',
				name: 'eventDate',
				format: 'd/m/Y',
				renderTo: 'eventDate',
				id: 'eventDate-id',
				maxValue: new Date()  // limited to the current date or prior
		});
		if(Ext.getCmp('eventTime-id'))
			Ext.getCmp('eventTime-id').destroy();
		Ext.create('Ext.form.field.Time',{
			name: 'eventTime',
			fieldLabel: 'Heure debut',
			minValue: '00:00 AM',
			maxValue: '11:59 PM',
			increment: 30,
			anchor: '100%',
			id: 'eventTime-id',
			renderTo: 'eventTime'
		});
	},
	
	generateID: function(){
		return Ext.data.IdGenerator.get('uuid').generate();
	},
	
	renderEventDate:function (value, p, r) {
		return Ext.String.format('{0}', Ext.Date.dateFormat(new Date(value), 'd/m/Y h:i a'), r.get('date'));
	},
	
	fillEventStore: function(){	
		var me = this;
		this.eventStoreId = this.generateID();
		 Ext.create('Ext.data.Store', {
			 model: 'Event',
			 pageSize: 5, // items per page
			 storeId: me.eventStoreId,
			 proxy: {
				 type: 'ajax',
				 url: tn.tunisietelecom.Constants.consultEventElements,
				 reader: {
					 type: 'json',
					 root: 'events',
					 totalProperty: 'totalCount'
				 }
			 },
			 autoLoad: true
		 });
	},
	
	fillEventGrid: function(){
		var me = this;
		this.eventGridId = this.generateID();
		Ext.create('Ext.grid.Panel', {
			title: 'Events',
			id: me.eventGridId,
			store: Ext.data.StoreManager.lookup(me.eventStoreId),
			columns: [
				{ dataIndex: 'idEvent', hidden: true },
				{ text: 'Site', dataIndex: 'site', align: 'center' },
				{ text: 'Locale', dataIndex: 'local', align: 'center' },
				{ text: 'Pi\u00E8ce', dataIndex: 'piece', align: 'center' },
				{ text: 'Libell\u00E9 alarme', dataIndex: 'cause', align: 'center', width:200 },
				{ text: 'Description', dataIndex: 'description', align: 'center' },
				{ text: 'Responsable', dataIndex: 'responsible', align: 'center' },
				{ text: 'Date cr\u00E9ation', dataIndex: 'date', width: 180, align: 'center', renderer: me.renderEventDate},
				{ text: 'Statut', dataIndex: 'status', xtype:'templatecolumn', tpl: me.statusTemplate() },
				{ dataIndex: 'ownStatus', hidden: true },
				{ dataIndex: 'workingInUserId', hidden: true }
			],
			height: 460,
			width: 'auto',
			renderTo: 'grid-event',
			dockedItems: [{
				xtype: 'toolbar',
				dock: 'top',
				items: me.getEventGridConsultToolbarItems()
			},{
					xtype: 'pagingtoolbar',
					store: Ext.data.StoreManager.lookup(me.eventStoreId),   // same store GridPanel is using
					dock: 'bottom',
					displayInfo: true
				}],
			listeners: {
				itemdblclick: function(grid, record, item, index, e, eOpts){
					if(record.get('ownStatus') == 0)
						me.updateEventStatus(record.get('idEvent'), 1);
					var isEventBeginner = (tn.tunisietelecom.User.currentUser > -1) && (tn.tunisietelecom.User.currentUser == record.get('workingInUserId'))
					Ext.create('Ext.window.Window', {
						title: 'Incident info',
						layout: {
		                    type: 'border',
		                    padding: 5
		                },
						height: 400,
						width: 400,
						modal: true,
						layout:'fit',						
						items: [{  // Let's put an empty grid in just to illustrate fit layout
							xtype: 'form',
							border: false,
							//width: 380,
						    // Fields will be arranged vertically, stretched to full width
							layout: 'anchor',
							defaults: {
								anchor: '100%'
								//width: 200 
							},
							 bodyPadding: 5,
							// The fields
							defaultType: 'textfield',
							items: [{
								fieldLabel: 'Site',
								name: 'site',
								readOnly: true,
								value: record.get('site')
							},{
								fieldLabel: 'Local',
								name: 'local',
								readOnly: true,
								value: record.get('local')
							},{
								fieldLabel: 'Piece',
								name: 'piece',
								readOnly: true,
								value: record.get('piece')
							},{
								fieldLabel: 'Cause',
								name: 'cause',
								readOnly: true,
								value: record.get('cause')
							},{
								fieldLabel: 'Description',
								name: 'description',
								readOnly: true,
								xtype: 'textarea',
								value: record.get('description')
							},{
								fieldLabel: 'Date',
								name: 'date',
								readOnly: true,
								value: Ext.Date.dateFormat(new Date(record.get('date')), 'd/m/Y h:i a')
							}]
						}],
						buttons: [{
							text: 'D\u00E9marrer',
							disabled: (record.get('status')!= 2)&&(record.get('status')!=3)? false: true,
							handler: function(){
								me.updateEventStatus(record.get('idEvent'), 2);
								this.up('window').close();
							}
						},{
							text: 'R\u00E9parer',
							disabled: (record.get('status')== 2) && isEventBeginner ? false: true,
							handler: function(){
								me.updateEventStatus(record.get('idEvent'), 3);
								this.up('window').close();
							}
						}]
					}).show();
				},
				itemcontextmenu: function(view, rec, node, index, e) {
                    e.stopEvent();
                    me.getGridMenu(rec).showAt(e.getXY());
                    return false;
                }
			}
		});
	},
	
	getEventGridConsultToolbarItems: function(){
		var me = this, items = [];
		items.push({
					text: 'Modifier'
				});
		if(tn.tunisietelecom.Role.canDelete()){
			items.push({
					text: 'Supprimer',
					handler: function(){
						var event = this.up('grid').getSelectionModel().getSelection();
						if(event.length > 0){
							var deleteFn = function(btn) {
								if (btn == 'yes'){
									Ext.Ajax.request({
										url: tn.tunisietelecom.Constants.deleteEvent,
										params: {
											idEvent: parseInt(event[0].get('idEvent'))
										},
										success: function(response, opts) {
											Ext.Msg.alert('Succ\u00E8s', 'Suppression avec succ\u00E8s.');
											Ext.data.StoreManager.lookup(me.eventStoreId).load();
										},
										failure: function(response, opts) {
											console.log('server-side failure with status code ' + response.status);
										}
									});
								}
							};
							Ext.Msg.show({
								 title:'Suppression',
								 msg: 'Voulez vous vraiment supprimer cet incident?',
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
	getGridMenu: function(record){
		var me = this;
		var viewRepAction = Ext.create('Ext.Action', {
			text: 'Afficher r\u00E9parateur',
			handler: function(widget, event) {
								var store =  Ext.create('Ext.data.Store', {
								 model: 'User',
								 proxy: {
									 type: 'ajax',
									 url: tn.tunisietelecom.Constants.consultWorkingInUser,
									 extraParams: {
										idEvent: record.get('idEvent')
									 },
									 reader: {
										 type: 'json',
										 root: ''
									 }
								 },
								 autoLoad: true
							 });
				var grid = Ext.create('Ext.grid.Panel', {
							store: store,
							columns: [
								{ dataIndex: 'userId', hidden: true },
								{ text: 'Nom', dataIndex: 'fullName', align: 'center' },
								{ text: 'Role', dataIndex: 'role', align: 'center' },
								{ text: 'Date d\u00E9marrage', dataIndex: 'startEventDate', width: 180, align: 'center', renderer: me.renderEventDate }
							 ]
							});
				Ext.create('Ext.window.Window', {
						title: 'Liste r\u00E9parateurs',
						height: 400,
						width: 400,
						modal: true,
						layout:'fit',						
						items: [grid]
				}).show();
			}
		});
		var viewAction = Ext.create('Ext.Action', {
			iconCls: 'buy-button',
			text: 'Afficher list des visiteurs',
			handler: function(widget, event) {
				var store =  Ext.create('Ext.data.Store', {
								 model: 'User',
								 proxy: {
									 type: 'ajax',
									 url: tn.tunisietelecom.Constants.consultNotifiedUser,
									 extraParams: {
										idEvent: record.get('idEvent')
									 },
									 reader: {
										 type: 'json',
										 root: ''
									 }
								 },
								 autoLoad: true
							 });
				var grid = Ext.create('Ext.grid.Panel', {
							store: store,
							columns: [
								{ dataIndex: 'userId', hidden: true },
								{ text: 'Nom', dataIndex: 'fullName', align: 'center' },
								{ text: 'Role', dataIndex: 'role', align: 'center' },
								{ text: 'Date consultation', dataIndex: 'readEventDate', width: 180, align: 'center', renderer: me.renderEventDate }
							 ]
							});
				Ext.create('Ext.window.Window', {
						title: 'Liste visiteurs',
						height: 400,
						width: 400,
						modal: true,
						layout:'fit',						
						items: [grid]
				}).show();
			}
		});

		return Ext.create('Ext.menu.Menu', {
			items: [
				viewRepAction,
				viewAction
			]
		});
	},
	
	updateEventStatus: function(idEvent, status){
		var me = this;
		Ext.Ajax.request({
			url: tn.tunisietelecom.Constants.updateEventStatus,
			params: {
				idEvent: idEvent,
				status: status
			},
			success: function(response, opts) {
				Ext.data.StoreManager.lookup(me.eventStoreId).load();
			},
			failure: function(response, opts) {
				console.log('server-side failure with status code ' + response.status);
			}
		});
	},
	
	statusTemplate: function(){
		return new Ext.XTemplate(
			'<tpl if="status &lt; 2 && ownStatus == 0">',
				'<img src="'+tn.tunisietelecom.Constants.notReadEventIcon+'" width=40>',
			'<tpl elseif="status &lt; 2 && ownStatus == 1">',
				'<img src="'+tn.tunisietelecom.Constants.readEventIcon+'" width=40>',
			'<tpl elseif="status == 2">',
				'<img src="'+tn.tunisietelecom.Constants.startedEventIcon+'" width=40>',
			'<tpl else>',
				'<img src="'+tn.tunisietelecom.Constants.finishedEventIcon+'" width=40>',
			'</tpl>'
		);
	},
	
	consultEvent: function(){
		this.fillEventStore();
		this.fillEventGrid();
	}
	
});