Ext.apply(tn.tunisietelecom.Chart, {
	
	generateID: function(){
		return Ext.data.IdGenerator.get('uuid').generate();
	},
	
	setUpStartDate: function(){
		if(Ext.getCmp('start-date-chart-id'))
			Ext.getCmp('start-date-chart-id').destroy();
		Ext.create('Ext.form.field.Date',{
				anchor: '100%',
				fieldLabel: 'De',
				name: 'from_date',
				format: 'd/m/Y',
				renderTo: 'start-date-chart',
				id: 'start-date-chart-id',
				maxValue: new Date()  // limited to the current date or prior
		});
		if(Ext.getCmp('start-time-chart-id'))
			Ext.getCmp('start-time-chart-id').destroy();
		Ext.create('Ext.form.field.Time',{
			name: 'start_time',
			fieldLabel: 'Heure debut',
			minValue: '00:00 AM',
			maxValue: '11:59 PM',
			increment: 30,
			anchor: '100%',
			id: 'start-time-chart-id',
			renderTo: 'start-time-chart'
		});
	},
	
	setUpEndDate: function(){
		if(Ext.getCmp('end-date-chart-id'))
			Ext.getCmp('end-date-chart-id').destroy();
		Ext.create('Ext.form.field.Date',{
				anchor: '100%',
				fieldLabel: 'Au',
				name: 'end_date',
				format: 'd/m/Y',
				renderTo: 'end-date-chart',
				id: 'end-date-chart-id',
				maxValue: new Date()  // limited to the current date or prior
		});
		if(Ext.getCmp('end-time-chart-id'))
			Ext.getCmp('end-time-chart-id').destroy();
		Ext.create('Ext.form.field.Time',{
			name: 'end_time',
			fieldLabel: 'Heure fin',
			minValue: '00:00 AM',
			maxValue: '11:59 PM',
			increment: 30,
			anchor: '100%',
			id: 'end-time-chart-id',
			renderTo: 'end-time-chart'
		});
	}
	
});