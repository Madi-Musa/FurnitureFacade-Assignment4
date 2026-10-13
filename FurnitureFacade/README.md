# Furniture Ordering System — Facade Pattern

Software Design Patterns — Assignment #4 (individual work)

## What the program does

A customer orders furniture through one simple method: `orderFurniture(item, quantity, city)`.
The facade checks inventory, calculates furniture and delivery prices, processes a simulated payment, and arranges delivery. If payment fails, reserved inventory is returned to stock.

## Classes

- `Main` — client; calls only the facade.
- `FurnitureFacade` — coordinates the ordering process; hides the subsystem workflow.
- `InventoryService` — tracks available products and reserves/releases units.
- `PricingService` — stores item prices and calculates totals.
- `PaymentService` — processes simulated payments against an in-memory customer balance.
- `DeliveryService` — validates supported cities, quotes a delivery fee and prepares delivery details.

The four subsystem classes do not refer to the facade. Advanced clients can create and use them independently.

## Run

Open the `src` folder in IntelliJ IDEA and run `Main.java`.

Or from a terminal in the project folder:

```bash
javac -d out src/*.java
java -cp out Main
```

## Sample cases

1. Two chairs in Astana: successful order.
2. Table in Shymkent: rejected because that city is unsupported.
3. Five wardrobes: rejected due to insufficient stock.
4. One wardrobe in Almaty: insufficient customer balance; reservation is undone.

Prices and payment balances are illustrative; this is a console demonstration, not a real payment provider.

## Five Clean Code principles

1. **Meaningful names:** `reserve`, `calculateGrandTotal`, and `orderFurniture` state their purpose.
2. **Single Responsibility Principle:** each subsystem handles a distinct domain concern; the facade coordinates rather than implementing the subsystem operations.
3. **Encapsulation:** the facade holds its subsystem objects in private fields; clients don't need their implementation details.
4. **Small public API:** the client places an order with one `orderFurniture` method rather than orchestrating four services.
5. **Error handling / consistent state:** bad quantities, unsupported cities, unavailable stock and insufficient payment are handled; failed payment releases reserved items.

## Why this is Facade

The client (`Main`) only creates `FurnitureFacade` and calls `orderFurniture`. The facade knows how to call the subsystem classes in the right sequence. The subsystem classes know nothing about the facade and remain available for direct use by advanced clients.

## Defense prompts

- What does Facade simplify? A complex workflow involving stock, pricing, payment and delivery.
- Is Facade the same as Adapter? No: Adapter translates incompatible interfaces; Facade provides an easier entry point to an existing subsystem.
- Why is rollback needed? Failed payment should not permanently consume reserved stock.
- Can subsystems be used without the facade? Yes; they are independent public classes.
