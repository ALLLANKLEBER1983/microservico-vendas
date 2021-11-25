import jwt from "jsonwebtoken";
import {promisify} from "util";

import * as httpStatus from "../constants/HttpStatus";
import * as secret from "../constants/Secret";
import AuthException from "./AuthException";

const emptySpace = " ";

export default async (req,res,next) => {

    try {
    const{autorization} = req.headers;
    if(!autorization){
        throw new AuthException(
            httpStatus.UNAUTHORIZED,
            "Access token was not informed."
        )
    }
    let accessToken = autorization;
    if(accessToken.includes(emptySpace)){
        accessToken = accessToken.split(emptySpace)[1];
    }
    const decoded = await promisify(jwt.verify)(
        accessToken,
        secret.API_SECRET
    );
    req.authUser = decoded.authUser;
    return next();
        
    } catch (err) {
        return res.status(err.status).json({
            status: err.status ? err.status : httpStatus.INTERNAL_SERVER_ERROR,
            message: err.status,
        }); 
        
    }
    

    }
