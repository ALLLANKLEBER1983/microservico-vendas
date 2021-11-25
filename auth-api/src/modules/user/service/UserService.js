import bcrypt from "bcrypt";
import jwt from "jsonwebtoken";

import UserRepository from  "../repository/UserRepository";
import * as HttStatus from "../../../config/constants/HttpStatus";
import UserException from "../exceptions/UserExceptions.js";
import * as secret from "../../../config/constants/Secret";




class UserService{
    async findByEmail(req){
        try{
            const{email} = req.params;
            this.validateRequestData(email);
            let user = await UserRepository.findByEmail(email);
            this.validateUserNotFound(user);

            return{
                status: HttStatus.SUCCESS,
                user: {
                    id: user.id,
                    name: user.name,
                    email: user.email,
                },
            };

        }catch(err){
            return{
                status: err.status ? err.status:HttStatus.INTERNAL_SERVER_ERROR,
                message: err.status,
            };
        }
    }

    validateRequestData(email){
        if(!email){
            throw new UserException(
                HttStatus.BAD_REQUEST,
                "User email was not informed.");
        }
    }

    validateUserNotFound(user){
        if(!user){
            throw new Error(HttStatus.BAD_REQUEST,"User was not found");
        }
    }
    async getAccessToken(req){
        try{
        const {email,password} = req.body;
        this.validateAcessToken(email,password);
        let user = await UserRepository.findByEmail(email);
        this.validateUserNotFound(user);
        await this.validatePassword(password,user.password);
        const authUser = {id: user.id, name: user.name, email: user.email}
        const accessToken = jwt.sign({authUser},
            secret.API_SECRET);
            return{
                status: HttStatus.SUCCESS,
                accessToken,

            };
        }catch(err){
            return{
                status: err.status ? err.status:HttStatus.INTERNAL_SERVER_ERROR,
                message: err.status,
            }; 

        }
        
    }

    validateAcessToken(email,password){
        if(!email || !password){
            throw new UserException(HttStatus.UNAUTHORIZED,"Email and password must be informed.");
        }
    }
    async validatePassword(password,hashPassword){
        if(!await bcrypt.compare(password,hashPassword)){
            throw new UserException(HttStatus.UNAUTHORIZED,"Password doesn't match.");
        }

    }

}

export default new UserService();