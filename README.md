# Supermarket Management System

## Overview
A JavaFX application built to model and manage a multi-level supermarket hierarchy (Floor Areas → Aisles → Shelves → Good Items) using custom data structures and object-oriented design principles.

## Features
* **Custom Hierarchy Management:** Dynamic creation and linking of Floor Areas, temperature-controlled Aisles (Unrefrigerated, Refrigerated, Frozen), Shelves, and individual Good Items.
* **Custom Data Structures:** Built-in generic Linked List implementations managing data storage, traversal, cost calculations, and stock breakdowns across the entire hierarchy.
* **Interactive GUI Map:** A visual JavaFX interface allowing users to drill down through floor levels, aisles, and shelves to inspect stock inventory.
* **Heuristic "Smart Add":** An intelligent placement feature that automatically routes items to suitable locations based on temperature requirements and existing similar inventory.
* **Item Search & Inventory Breakdown:** Full-text search returning precise physical locations (Floor, Aisle, Shelf) along with dynamic price and quantity valuations.
* **Data Persistence & Reset:** Full system state save and load functionality using object serialization alongside a system reset utility.
* **Automated Testing:** Test suite implemented with JUnit verifying data structure integrity and hierarchy logic.

## Technologies
* Java
* JavaFX
* JUnit 5
* Maven

## Core Algorithms & Concepts
* **Data Structures:** Custom Generic Linked Lists, Abstract Data Types (ADTs)
* **Algorithms:** Heuristic Item Placement, Full-Tree Hierarchy Traversals, Deep Object Cloning
* **Design Patterns:** Model-View-Controller (MVC), Data Persistence

## Notes
Developed as an individual Continuous Assessment project for the Data Structures & Algorithms module.
