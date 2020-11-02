# SuperBenchmarker

------
开源的类似于Apache ab的压力测试命令行工具
SuperBenchmarker (sb.exe) supports PUT, DELETE, POST or any arbitrary method

[github Link](https://github.com/aliostad/SuperBenchmarker)

- demo
```shell
λ sb -u https://www.qq.com -n 1000
Starting at 2020/11/2 18:23:32
[Press C to stop the test]
1000    (RPS: 24.4)
---------------Finished!----------------
Finished at 2020/11/2 18:24:13 (took 00:00:40.9917376)
Status 200:    1000

RPS: 23.9 (requests/second)
Max: 1044ms
Min: 26ms
Avg: 38.8ms

  50%   below 30ms
  60%   below 31ms
  70%   below 32ms
  80%   below 34ms
  90%   below 44ms
  95%   below 73ms
  98%   below 171ms
  99%   below 230ms
99.9%   below 1044ms
```



