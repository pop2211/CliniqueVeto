USE Clinique_veto;  
GO  

ALTER TABLE dbo.Agendas
DROP CONSTRAINT FK_Agendas;  
GO  

ALTER TABLE dbo.Agendas
DROP CONSTRAINT FK_Agendas_Veto;  
GO  

ALTER TABLE dbo.Animaux
DROP CONSTRAINT FK_Animaux;  
GO  

ALTER TABLE dbo.Animaux
DROP CONSTRAINT FK_ANIMAUX_RACES;  
GO  


truncate table dbo.Agendas;
truncate table dbo.Personnels;
truncate table dbo.Clients;
truncate table dbo.Animaux;

GO

ALTER TABLE dbo.Agendas ADD CONSTRAINT
	FK_Agendas FOREIGN KEY 	( CodeAnimal )
	references Animaux (CodeAnimal ) 
	
ALTER TABLE dbo.Agendas ADD CONSTRAINT
	FK_Agendas_Veto FOREIGN KEY 	( CodeVeto )
	references Personnels ( CodePers ) 
	
ALTER TABLE dbo.Animaux ADD CONSTRAINT
	FK_Animaux FOREIGN KEY 	( CodeClient )
	references Clients (CodeClient )

ALTER TABLE dbo.Animaux ADD CONSTRAINT
	FK_ANIMAUX_RACES FOREIGN KEY (Race,Espece)
	references Races (Race,Espece)	

