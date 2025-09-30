# Proxy Pattern – Video Service Example

## Overview
The **Proxy Pattern** is a structural design pattern that provides a surrogate or placeholder for another object to control access to it.  
A proxy can perform additional tasks (like access control, caching, logging) before delegating requests to the real object.

---

##  Problem
In streaming services like Netflix:
- Directly exposing the real service may allow **unauthorized access**.  
- Without a proxy, the client always interacts with the real object, making it harder to enforce restrictions.  
- Adding checks directly into the real service class leads to **violating Single Responsibility** and code bloat.

---

##  Solution
The Proxy pattern solves this problem by:
- Defining a **common interface** (`VideoService`) for both the real service and proxy.  
- Implementing the real logic in `NetflixService`.  
- Wrapping it with a `VideoServiceProxy` that performs **subscription checks** before streaming.  
- Keeping access control **separate** from core functionality.

---