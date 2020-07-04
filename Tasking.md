**Tasking 普通用户**
```

---存包------
1 given 一个s号的包，和一个s号的locker 有剩余空间
when 小樱存包
then 存包成功，产生票据

2 given 一个s号的包，和一个s号的locker 没有剩余空间
  when 小樱存包
  then 存包失败，提示箱子已满

3 given 一个M号的包，和一个s号的locker 和一个PrimaryLockerRobot管理一个M号的Locker,有剩余空间
  when 小樱存包
  then 存包成功，产生票据

4 given 一个M号的包，和一个s号的locker 和一个PrimaryLockerRobot管理一个M号的Locker,没有有剩余空间
    when 小樱存包
    then 存包失败，提示箱子已满

5 given 一个L号的包，和一个s号的locker 和一个PrimaryLockerRobot管理一个M号的Locker和一个SuperLockerRobot管理的L号的Locker,有有剩余空间
   when 小樱存包
   then 存包成功，返回票据

6 given 一个L号的包，和一个s号的locker 和一个PrimaryLockerRobot管理一个M号的Locker和一个SuperLockerRobot管理的L号的Locker,没有剩余空间
   when 小樱存包
   then 存包失败，提示箱子已满

---取包----
1 given 让小樱存了一个s号的包，并且给它一个有效票据
  when 小樱去Locker取包
  then 取包成功

2 given 让小樱存了一个s号的包，并且给它一个无效票据
  when 小樱去Locker取包
  then 取包失败，票据无效

3 given 让小樱存了一个m号的包，并且给它一个有效票据
  when 小樱去PrimaryLockerRobot取包
  then 取包成功

4 given 让小樱存了一个m号的包，并且给它一个无效票据
  when 小樱去PrimaryLockerRobot取包
  then 取包失败，票据无效

5 given 让小樱存了一个L号的包，并且给它一个有效票据
  when 小樱去SuperLockerRobot取包
  then 取包成功
--------------失败----------------------------
6 given 让小樱存了一个L号的包，并且给它一个无效票据
  when 小樱去SuperLockerRobot取包
  then 取包失败，票据无效
--------------型号不对-------------------------
7 given 让小樱存了一个L号的包，并且给它一个有效票据
  when 小樱去PrimaryLockerRobot取包
  then 取包失败，票的型号不对

8 given 让小樱存了一个s号的包，并且给它一个有效票据
  when 小樱去SuperLockerRobot取包
  then 取包失败，票的型号不对

9 given 让小樱存了一个L号的包，并且给它一个有效票据
  when 小樱去 Locker取包
  then 取包失败，票的型号不对

```


**Tasking VIP用户**
```
---存包------
1 given 一个s号的包，manager管理一个s号的locker 有剩余空间
when manager存包
then 存包成功，产生票据

2 given 一个s号的包，manager管理一个s号的locker 没有剩余空间
  when manager存包
  then 存包失败，提示箱子已满

3 given 一个M号的包，manager管理一个s号的locker 和一个PrimaryLockerRobot管理一个M号的Locker,有剩余空间
  when manager存包
  then 存包成功，产生票据

4 given 一个M号的包，manager管理一个s号的locker 和一个PrimaryLockerRobot管理一个M号的Locker,没有有剩余空间
    when manager存包
    then 存包失败，提示箱子已满

5 given 一个L号的包，manager管理一个s号的locker 和一个PrimaryLockerRobot管理一个M号的Locker和一个SuperLockerRobot管理的L号的Locker,有有剩余空间
   when manager存包
   then 存包成功，返回票据

6 given 一个L号的包，manager管理一个s号的locker 和一个PrimaryLockerRobot管理一个M号的Locker和一个SuperLockerRobot管理的L号的Locker,没有剩余空间
   when manager存包
   then 存包失败，提示箱子已满

---取包----
1 given 让manager存了一个s号的包，并且给它一个有效票据
  when manager去Locker取包
  then 取包成功

2 given 让manager存了一个s号的包，并且给它一个无效票据
  when manager去Locker取包
  then 取包失败，票据无效

3 given 让manager存了一个m号的包，并且给它一个有效票据
  when manager去PrimaryLockerRobot取包
  then 取包成功

4 given 让manager存了一个m号的包，并且给它一个无效票据
  when manager去PrimaryLockerRobot取包
  then 取包失败，票据无效

5 given 让manager存了一个L号的包，并且给它一个有效票据
  when manager去SuperLockerRobot取包
  then 取包成功
--------------失败----------------------------
6 given 让manager存了一个L号的包，并且给它一个无效票据
  when manager去SuperLockerRobot取包
  then 取包失败，票据无效
--------------型号不对-------------------------
7 given 让manager存了一个L号的包，并且给它一个有效票据
  when manager去PrimaryLockerRobot取包
  then 取包失败，票的型号不对

8 given 让manager存了一个s号的包，并且给它一个有效票据
  when manager去SuperLockerRobot取包
  then 取包失败，票的型号不对

9 given 让manager存了一个L号的包，并且给它一个有效票据
  when manager去 Locker取包
  then 取包失败，票的型号不对
```
**Tasking 配置Rebot和Manger**
```
1 given 给manager配一个s号的柜子
  when 
  then 配置成功

2 given 给manager配一个M号的柜子
  when 
  then 配置失败

3 given 给PrimaryLockerRobot配一个S号的柜子
  when 
  then 配置失败

4 given 给PrimaryLockerRobot配一个M号的柜子
  when 
  then 配置成功

5 given 给SuperLockerRobot 配一个L号的柜子
  when 
  then 配置成功

5 given 给SuperLockerRobot 配一个S号的柜子
  when 
  then 配置失败
```
