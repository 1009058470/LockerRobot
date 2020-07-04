```
华顺超市准备购买三种型号的储物柜，分别为S，M，L（S < M < L）。当顾客来
存包的时候只需要将包交给小樱，之后的一系列存包会由小樱来完成。

小樱在存包之前先会拿到包裹的尺寸标签，根据不同的尺寸标签决定是直接
存入Locker还是找对应Robot存包。当包裹尺寸为S时，小樱会直接存入S号的
Locker中；当包裹尺寸为M时，找PrimaryLockerRobot存包；当包裹尺寸为L时
，找SuperLockerRobot存包。存包成功后小樱会将票据交给顾客。存包的时候
，小樱从不犯糊涂，她一定能找对目标。

当普通顾客拿着票据来取包的时候，只要把票据交给小樱，小樱会找对应的
Robot或者Locker取包。

当VIP顾客来存取包时，可以直接通过VIP通道找LockerRobotManager提供专
门的存取包服务。
```

1 Locker可以存包取包

2 PrimaryLockerRobot 按照Locker顺序存，它只管理M号Locker，暂且不用考虑管理其它型号的Locker。

3 SuperLockerRobot 将包存入空置率最大的Locker，它只管理L号Locker，暂且不用考虑管理其它型号的Locker。

4 目前由于业务量比较小，LockerRobotManager只管理一个Locker（S号）、一个PrimaryLockerRobot（管理一个Locker）和SuperLockerRobot（管
理一个Locker），但也不排除后期随着业务增长，LockerRobotManager会管理更多的Locker或者Robot

5 LockerRobotManager可以委派Robot存包取包，也可以自己存包取包，委派顺序没有要求。

6 LockerRobotManager管理的Locker和Robot不会直接对外提供服务

8 超市管理员在配置Robot和Manager的时候，只要Locker的型号选择不对，Robot和Manager将无法正常使用。

7 不同型号Locker产生的票据不通用，当用不同的型号票取包时，系统要提示票的型号不对