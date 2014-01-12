Ext.apply(tn.tunisietelecom.Role, {
	BOSSUNIT: "4",
	EESAGENT: "2",
	SUBCONTRACTOR: "3",
	ADMINISTRATOR: "1",
	
	currentUserRole: '',
	
	canDelete: function(){
		if (this.currentUserRole == this.ADMINISTRATOR)
			return true;
		else 
			return false;
	},
	
	canDeleteAndModify: function(){
		if(this.currentUserRole == this.ADMINISTRATOR || this.currentUserRole == this.EESAGENT)
			return true;
		else 
			return false;
	},
	
	canLoadExtraCharts: function(){
		if(this.currentUserRole != this.SUBCONTRACTOR && this.currentUserRole != this.BOSSUNIT)
			return false;
		else 
			return true;
	},
	
	isSubcontractor: function(){
		if (this.currentUserRole == this.SUBCONTRACTOR)
			return true;
		else 
			return false;
	},

	isAdministrator: function(){
		if (this.currentUserRole == this.ADMINISTRATOR)
			return true;
		else 
			return false;
	},
	
	isBossUnit: function(){
		if (this.currentUserRole == this.BOSSUNIT)
			return true;
		else 
			return false;
	},
	
	isEssAgent: function(){
		if (this.currentUserRole == this.EESAGENT)
			return true;
		else 
			return false;
	},
	 isEESAgent: function(){
		  if (this.currentUserRole == this.EESAGENT)
		   return true;
		  else 
		   return false;
		 }
	

});