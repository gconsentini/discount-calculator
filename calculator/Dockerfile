FROM node:16

WORKDIR /app
COPY . .
RUN yarn install --production
EXPOSE 3336

CMD ["node", "src/index.js"]