const productClient = require('./services/product');
const userClient = require("./services/user");

Date.prototype.isSameDateAs = function (pDate) {

  console.log(this.getDate());
  console.log(pDate.getDate());

  return (
    this.getMonth() === pDate.getMonth() &&
    this.getDate() === pDate.getDate()
  );
};

async function getUserById(id){
  const userResponse = await new Promise((resolve, reject) => {
    userClient.getUserById({ id }, (err, response) => {
      if (err) {
        reject(err);
      } else {
        resolve(response);
      }
    });
  });
  return userResponse;
};

async function getProductById(id) {
  const productResponse = await new Promise((resolve, reject) => {
    productClient.getProductById({ id }, (err, response) => {
      if (err) {
        reject(err);
      } else {
        resolve(response);
      }
    });
  });
  return productResponse;
};

function calculateDiscount(percentage, priceInCents){
  return Math.round(priceInCents*percentage);
}

function getDiscountByUser(user, product){
  const blackFriday = new Date(process.env.BLACK_FRIDAY_DATE);
  const blackFridayDiscount = Number(process.env.BLACK_FRIDAY_DISCOUNT);
  const birthdayDiscount = Number(process.env.BIRTHDAY_DISCOUNT);
  const today = new Date();
  let percentage = 1;

  console.log(today);

  if (today.isSameDateAs(blackFriday))
    percentage -= blackFridayDiscount;
  else if (today.isSameDateAs(new Date(user.date_of_birth)))
    percentage -= birthdayDiscount;
  
  return {
    discount: {
      percentage: Number(1-percentage).toFixed(2),
      value_in_cents: calculateDiscount(percentage, product.price_in_cents),
    },
  };
}

module.exports = {
  async getProductDiscountByUserId(call, callback) {
    const { user_id, product_id } = call.request;

    const userResponse = await getUserById(user_id);
    const productResponse = await await getProductById(product_id);


    const value = {
      ...getDiscountByUser(userResponse.user, productResponse.product),
    };
    
    console.log(value);

    return callback(null, {
      ...getDiscountByUser(userResponse.user, productResponse.product),
    });
  },
};
