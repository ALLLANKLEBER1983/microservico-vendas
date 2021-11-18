From node:16
workdir .
COPY package*.json ./
RUN yarn
COPY . . 
EXPOSE 8082
CMD ["node","app.js"]