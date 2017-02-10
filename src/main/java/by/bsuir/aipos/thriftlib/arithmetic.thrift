# This will set the generated java class
# in kmv.thrift package
namespace java by.bsuir.aipos.thriftserver

typedef i32 int

typedef i64 long
typedef i32 int

service ArithmeticService {  // defines simple arithmetic service
            long add(1:int num1, 2:int num2),
            long multiply(1:int num1, 2:int num2),
}