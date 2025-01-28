#!/bin/bash

pip install -r statistics/requirements.txt

java MainForStatistics.java wvcp-instances/vc_20_60_01.txt 500 5 10
java MainForStatistics.java wvcp-instances/vc_100_500_01.txt 500 5 10
java MainForStatistics.java wvcp-instances/vc_800_10000.txt 500 5 10

java MainForStatistics.java wvcp-instances/vc_20_60_01.txt 500 7 20
java MainForStatistics.java wvcp-instances/vc_100_500_01.txt 500 7 20
java MainForStatistics.java wvcp-instances/vc_800_10000.txt 500 7 20

java MainForStatistics.java wvcp-instances/vc_20_60_01.txt 1000 5 20
java MainForStatistics.java wvcp-instances/vc_100_500_01.txt 1000 5 20
java MainForStatistics.java wvcp-instances/vc_800_10000.txt 1000 5 20

java MainForStatistics.java wvcp-instances/vc_20_60_01.txt 1000 10 30
java MainForStatistics.java wvcp-instances/vc_100_500_01.txt 1000 10 30
java MainForStatistics.java wvcp-instances/vc_800_10000.txt 1000 10 30

java MainForStatistics.java wvcp-instances/vc_20_60_01.txt 2000 7 10
java MainForStatistics.java wvcp-instances/vc_100_500_01.txt 2000 7 10
java MainForStatistics.java wvcp-instances/vc_800_10000.txt 2000 7 10

java MainForStatistics.java wvcp-instances/vc_20_60_01.txt 2000 10 20
java MainForStatistics.java wvcp-instances/vc_100_500_01.txt 2000 10 20
java MainForStatistics.java wvcp-instances/vc_800_10000.txt 2000 10 20

python3 statistics/plot_scalability.py