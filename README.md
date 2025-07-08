# Simple E-Commerce System (Java)

This project is a simple shopping system made in Java.

## Features

- Products have:
  - Name
  - Price
  - Quantity
  - Some expire (like Cheese, Biscuits)
  - Some need shipping (like Cheese, TV)
  - Some don’t need shipping (like Mobile Scratch Cards)

- Shippable products have a weight.

## What Customers Can Do

- Add products to cart (not more than available quantity)
- Checkout:
  - Shows:
    - Total price (subtotal)
    - Shipping cost
    - Final amount (subtotal + shipping)
    - Remaining balance
  - Sends shippable items to ShippingService

## Errors

Checkout will show error if:
- Cart is empty
- Not enough balance
- Product is expired or out of stock

## Output Screenshot
![image](https://github.com/user-attachments/assets/ddd5a9e3-0592-47d0-a2cd-8c9ac897764e)
![image](https://github.com/user-attachments/assets/ca8213d5-ddfe-454d-b400-7e31025b204e)


