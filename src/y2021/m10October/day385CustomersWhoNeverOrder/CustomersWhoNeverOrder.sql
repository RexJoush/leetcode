/**
 * @author Rex Joush
 * @time 2021.10.27
 */

/*
    从不订购的客户
    https://leetcode-cn.com/problems/customers-who-never-order/

    某网站包含两个表，Customers 表和 Orders 表。编写一个 SQL 查询，找出所有从不订购任何东西的客户。
    Customers 表：
        +----+-------+
        | Id | Name  |
        +----+-------+
        | 1  | Joe   |
        | 2  | Henry |
        | 3  | Sam   |
        | 4  | Max   |
        +----+-------+

    Orders 表：
        +----+------------+
        | Id | CustomerId |
        +----+------------+
        | 1  | 3          |
        | 2  | 1          |
        +----+------------+

    例如给定上述表格，你的查询应返回：
        +-----------+
        | Customers |
        +-----------+
        | Henry     |
        | Max       |
        +-----------+
*/

/*
    1.首先查询 order 表中的所有 customerId，
    再查询 customer 表中不在此子查询中的结果
    结果：
        568 ms, 46.00%
        0 MB, 100.00%

*/
SELECT Customers.Name as Customers
FROM Customers
WHERE Customers.Id NOT IN (SELECT CustomerId FROM Orders);

/*
    2.左外连接
    customer 表左外连接查询结果如下
    +----+-------+------+-----------+
    | Id | Name  |Id(1) |CustomerID |
    +----+-------+------|-----------|
    | 1  | Joe   |  2   |   1       |
    | 2  | Henry | null |  null     |
    | 3  | Sam   |  1   |   3       |
    | 4  | Max   | null |  null     |
    +----+-------+------+-----------+
    当 order 表中没有信息的 customer 中，CustomerID 将为 null，获取所有值 null 的即可
    结果：
        512 ms, 92.63%
        0 MB, 100.00%
 */

SELECT Name AS Customers
FROM Customers
    LEFT JOIN Orders ON Orders.CustomerId = Customers.Id
WHERE CustomerId IS NULL;