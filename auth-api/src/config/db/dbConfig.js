import Sequelize  from "sequelize";

const sequelize = new Sequelize("auth-db","postgres","32022212",{
    host:"localhost",
    dialect:"postgres",
    quoteIdentifiers:false,
    define:{
        syncOnAssociation:true,
        timestamps:false,
        underscoredAll:true,
        freezeTableName:true

    },
    
});

sequelize.authenticate().than(() =>{
    console.info("Connection has been stablished!");
})
.catch((err) =>{
    console.error("unable to connect to the database.");
    console.error(err.message);
});

export default sequelize;
