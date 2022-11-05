#include <Arduino.h> 
#include <Wire.h> 
#include <SoftwareSerial.h>
SoftwareSerial BTSerial(6,7);
 
void mpu6050_init(); 
void accel_calculate(); 
void value_init(); 
void Emergency_state_(); 
 
long ac_x, ac_y, ac_z, gy_x, gy_y, gy_z ;
double shock = 0, deg ;
double angle1 = 0;
double dgy_x ; 
long int normal_x,normal_y,normal_z, deltha_x[3],deltha_y[3],deltha_z[3], Acc, angle_1[3], angle_2[3] ; 
long int Shock;
long int NOMAL_ANGLE = 70;
long int Slope = 0;
const int pin = A0;
const int mapping_value = 1000;
const int delay_main = 2;
const int sum_count = 2; 
boolean State_Parameter = false;
#define Pin_Relay 12
#define mpu_add 0x68
 
void setup(){
  Serial.begin(9600);  
  BTSerial.begin(9600);
  mpu6050_init();
  pinMode(Pin_Relay, OUTPUT);
  digitalWrite(Pin_Relay , HIGH);
  Serial.println("ANSIME,ACC,Shock,Slope");
}

void loop()
{
  State_Parameter = false;
  value_init(); 
  accel_calculate();
  Slope = abs(angle1);

  for (int i=0; i < sum_count; i++){ accel_calculate();
  deltha_x[1] = deltha_x[1]+(normal_x); deltha_y[1] = deltha_y[1]+(normal_y); deltha_z[1] = deltha_z[1]+(normal_z); Shock = shock;}
  deltha_x[1] = int(deltha_x[1]/sum_count); deltha_y[1] = int(deltha_y[1]/sum_count); deltha_z[1] = int(deltha_z[1]/sum_count);

  for (int i=0; i < sum_count; i++){ accel_calculate();
  deltha_x[2] = deltha_x[2]+(normal_x); deltha_y[2] = deltha_y[2]+(normal_y); deltha_z[2] = deltha_z[2]+(normal_z); Shock = shock;}
  deltha_x[2] = int(deltha_x[2]/sum_count); deltha_y[2] = int(deltha_y[2]/sum_count); deltha_z[2] = int(deltha_z[2]/sum_count);

  deltha_x[0] = abs(deltha_x[1]-deltha_x[2]); deltha_y[0] = abs(deltha_y[1]-deltha_y[2]); deltha_z[0] = abs(deltha_z[1]-deltha_z[2]);
  Acc = deltha_x[0] + deltha_y[0] + deltha_z[0];
  Shock = abs(int(1024 - Shock));

  if (Slope > 70){State_Parameter=true;}

  if( State_Parameter == true ){Emergency_state_();}else{
  Serial.print( "Daily, " );
  Serial.print(Acc);
  Serial.print(",");
  Serial.print(Shock);
  Serial.print(",");
  Serial.println(Slope);
  }
  delay(1000);
}

// 비상함수 - LED ON, 부저 ON
void Emergency_state_(){
  Serial.print( "Fall, " );
  Serial.print(Acc);
  Serial.print(",");
  Serial.print(Shock);
  Serial.print(",");
  Serial.println(Slope);

  // Serial.println( "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!" );
  // Serial.println( " 낙상이 감지되었습니다 " );
  // Serial.print( " 가속도 변화량 : " );
  // Serial.print(Acc);
  // Serial.println();
  // Serial.print(" 현재 진동 값 : ");
  // Serial.print(Shock);
  // Serial.println();
  // Serial.print(" 기울기 변화량 : ");
  // Serial.print(Slope);
  // Serial.println();

  BTSerial.println( " 낙상이 감지되었습니다 " );
  BTSerial.print( " 가속도 변화량 : " );
  BTSerial.print(Acc);
  BTSerial.println();
  BTSerial.print(" 현재 진동 값 : ");
  BTSerial.print(Shock);
  BTSerial.println();
  BTSerial.print("기울기 변화량 : ");
  BTSerial.print(Slope);
  BTSerial.println();
  State_Parameter == false;
  delay(30000);
}

void value_init(){ 
  normal_x = 0; normal_x = 0; normal_x = 0;
  for (int i=0; i < 3; i++){ deltha_x[i]=0; deltha_y[i]=0; deltha_z[i] = 0; shock = 0; Shock=0;}
}


void mpu6050_init(){
  Wire.begin(); 
  Wire.beginTransmission(mpu_add) ; 
  Wire.write(0x6B) ; 
  Wire.write(0) ;
  Wire.endTransmission(true) ;
}

void accel_calculate() {
 
 
  ac_x = 0; ac_y = 0; ac_z = 0;
  normal_x = 0; normal_x = 0; normal_x = 0;

  Wire.beginTransmission(mpu_add) ; // 번지수 찾기
  Wire.write(0x3B) ; // 가속도 데이터 보내달라고 컨트롤 신호 보내기
  Wire.endTransmission(false) ; // 기달리고,
  Wire.requestFrom(mpu_add, 6, true) ; // 데이터를 받아 처리
 

  // Data SHIFT
  ac_x = Wire.read() << 8 | Wire.read() ;
  ac_y = Wire.read() << 8 | Wire.read() ;
  ac_z = Wire.read() << 8 | Wire.read() ;
 
 
//맵핑화 시킨 것 - 즉 10000으로 맵핑시킴
  normal_x = map(int(ac_x), -16384, 16384, 0, mapping_value);
  normal_y = map(int(ac_y), -16384, 16384, 0, mapping_value);
  normal_z = map(int(ac_z), -16384, 16384, 0, mapping_value);
 
 
//각도계산 deg -> 각도
  shock = analogRead(pin);
  
  float accel_xz, accel_yz;
  const float RADIANS_TO_DEGREES = 180/3.14159;

  // 각도1 계산하기
  accel_yz = sqrt(pow(ac_x, 2) + pow(ac_y, 2));
  angle1 = atan(-ac_z / accel_yz)*RADIANS_TO_DEGREES;

  // 각도2 계산하기
  accel_xz = sqrt(pow(ac_z, 2) + pow(ac_y, 2));
}