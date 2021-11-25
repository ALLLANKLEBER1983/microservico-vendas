import express from 'express';

import * as db from "./src/config/db/initialData.js";
import UserRoutes from "./src/modules/user/routes/UserRoutes"


const app = express();
const env = process.env;
const PORT = env.PORT || 8080;

db.createInitialData();

app.use(express.json());

app.use(UserRoutes);
app.listen(PORT,() =>{
    console.info(`Server started successfuly at port ${PORT}`);
})
