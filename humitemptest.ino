#include <Adafruit_Sensor.h>

#include <DHT.h>
#include <DHT_U.h>
#include <Wire.h>
char HUMI_Sensor = 3; //A0에 센서연결
byte dht11_dat[5];   //센서변수
byte dht11_in;       //센서변수
byte i;
byte read_dht11_dat()  //습온센서값 읽어오는 함수
{
  byte i = 0;          //for문 변수
  byte result = 0;     //리턴변수
  for (i = 0; i < 8; i++) //for문 9번 반복
  {
    while (!digitalRead(HUMI_Sensor));  //센서값이 1이 될때까지 무한루프
    delayMicroseconds(30);              //30마이크로세크 대기
    if (digitalRead(HUMI_Sensor) != 0 ) //만약 샌서값이 0과 같지 않으면
      bitSet(result, 7 - i);            //bitSet(n,x) n이라는 변수에 x번째의 비트를 on한다
    while (digitalRead(HUMI_Sensor));  //센서값이 0이 될때까지 무한루프
  }
  return result;                       //result의 값을 리턴한다.
}
void setup()
{
  Serial.begin(9600);

  pinMode(HUMI_Sensor, OUTPUT);           //센서핀을 출력으로 설정한다.
  digitalWrite(HUMI_Sensor, HIGH);        //센서에 출력을 넣는다
}
void loop()
{
  digitalWrite(HUMI_Sensor, LOW);//센서핀을 off한다
  delay(18);                     //18ms만큼 지연
  digitalWrite(HUMI_Sensor, HIGH);//센서핀을 on한다
  delayMicroseconds(5);            //5us만큼 지연
  pinMode(HUMI_Sensor, INPUT);    //센서핀을 입력으로 설정한다
  delayMicroseconds(200);         //200us만큼 지연
  for (i = 0; i < 5; i++) {
    dht11_dat[i] = read_dht11_dat();
  }
  //40bit의 데이터를 수신 자세한 사항은 DHT11의 데이터시트를 참고
  pinMode(HUMI_Sensor, OUTPUT);    //센서핀을 출력으로 설정한다
  digitalWrite(HUMI_Sensor, HIGH); //센서에 출력을 넣는다
  byte dht11_check_sum = dht11_dat[0] + dht11_dat[2]; // check check_sum
  Serial.print(dht11_dat[0], DEC);   // LCD에 습도값 출력
  Serial.print('%');                  // LCD에 % 출력
  Serial.print(dht11_dat[2], DEC);    // LCD에 온도값 출력
  Serial.println('C');                  // LCD에 C 출력
  delay(2000); //2초간 지연
}
