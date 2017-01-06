package com.szl.networks;

import java.io.IOException;
import java.util.Vector;

/**
 * Created by zsc on 2017/1/2.
 * 神经网络 java
 */
public class Networks {
    static int LAYER = 3;       //三层神经网络
    int NUM = 10;       //每层的最多节点数

    double A = 30.0;
    double B = 10.0;     //A和B是S型函数的参数
    double ITERS = 8174;    //最大训练次数  8174
    double ETA_W = 0.0035;   //权值调整率n
    double ETA_B = 0.001;    //阀值调整率n
    double ERROR = 0.002;    //单个样本允许的误差
    double ACCU = 0.005;    //每次迭代允许的误差
    double e = 2.718281828459;

    int in_num;                 //输入层节点数
    int ou_num;                 //输出层节点数
    int hd_num;                 //隐含层节点数

    Vector<Data> data;          //输入输出数据

    public double[][][] w = new double[LAYER][NUM][NUM];    //BP网络的权值
    public double[][] b = new double[LAYER][NUM];         //BP网络节点的阀值
    public double[][] x = new double[LAYER][NUM];         //每个神经元的值经S型函数转化后的输出值，输入层就为原值
    public double[][] d = new double[LAYER][NUM];         //记录delta学习规则中delta的值

    public void GetData(Vector<Data> a) {
        data = a;
    }

    public void Train() {
        System.out.println("Begin to train BP NetWork!");
        GetNums();

        int num = data.size();//41

        for (int iter = 0; iter <= ITERS; iter++) {
            for (int cnt = 0; cnt < num; cnt++) {
                //第一层输入节点赋值
                for (int i = 0; i < in_num; i++)
                    x[0][i] = data.elementAt(cnt).x.elementAt(i);

                while (true) {
                    ForwardTransfer();
                    if (GetError(cnt) < ERROR)    //如果误差比较小，则针对单个样本跳出循环
                        break;
                    ReverseTransfer(cnt);
                }
            }
            System.out.println("This is the trainning NetWork " + iter);

            double accu = GetAccu();
            System.out.println("All Samples Accuracy is " + accu);
            if (accu < ACCU) break;
        }
        System.out.println("The BP NetWork train End!");
    }

    public Vector<Double> ForeCast(Vector<Double> data) {
        int n = data.size();
        assert (n == in_num);
        for (int i = 0; i < in_num; i++)
            x[0][i] = data.elementAt(i);

        ForwardTransfer();
        Vector<Double> v = new Vector<Double>();
        for (int i = 0; i < ou_num; i++)
            v.addElement(x[2][i]);
        return v;
    }
    //public  void InitNetWork(){}         //初始化网络   Java 初始化全部置为0；

    public void GetNums() {
        in_num = data.elementAt(0).x.size();                         //获取输入层节点数3
        ou_num = data.elementAt(0).y.size();                         //获取输出层节点数1
        hd_num = (int) Math.sqrt((in_num + ou_num) * 1.0) + 5;   //获取隐含层节点数7
        System.out.println("in_num " + in_num);
        System.out.println("ou_num " + ou_num);
        System.out.println("hd_num " + hd_num);
        if (hd_num > NUM) hd_num = NUM;                     //隐含层数目不能超过最大设置
    }             //获取输入、输出和隐含层节点数

    public void ForwardTransfer() {
        //计算隐含层各个节点的输出值
        for (int j = 0; j < hd_num; j++) {
            double t = 0;
            for (int i = 0; i < in_num; i++)
                t += w[1][i][j] * x[0][i];
            t += b[1][j];
            x[1][j] = Sigmoid(t);
        }

        //计算输出层各节点的输出值
        for (int j = 0; j < ou_num; j++) {
            double t = 0;
            for (int i = 0; i < hd_num; i++)
                t += w[2][i][j] * x[1][i];
            t += b[2][j];
            x[2][j] = Sigmoid(t);
        }
    }     //正向传播子过程   有问题！！！

    public void ReverseTransfer(int cnt) {
        CalcDelta(cnt);
        UpdateNetWork();
    } //逆向传播子过程

    public void CalcDelta(int cnt) {               //计算w和b的调整量
        //计算输出层的delta值
        for (int i = 0; i < ou_num; i++)
            d[2][i] = (x[2][i] - data.elementAt(cnt).y.elementAt(i)) * x[2][i] * (A - x[2][i]) / (A * B);
        //计算隐含层的delta值
        for (int i = 0; i < hd_num; i++) {
            double t = 0;
            for (int j = 0; j < ou_num; j++)
                t += w[2][i][j] * d[2][j];
            d[1][i] = t * x[1][i] * (A - x[1][i]) / (A * B);
        }
    }

    public void UpdateNetWork() {
        //隐含层和输出层之间权值和阀值调整
        for (int i = 0; i < hd_num; i++) {
            for (int j = 0; j < ou_num; j++)
                w[2][i][j] -= ETA_W * d[2][j] * x[1][i];
        }
        for (int i = 0; i < ou_num; i++)
            b[2][i] -= ETA_B * d[2][i];

        //输入层和隐含层之间权值和阀值调整
        for (int i = 0; i < in_num; i++) {
            for (int j = 0; j < hd_num; j++)
                w[1][i][j] -= ETA_W * d[1][j] * x[0][i];
        }
        for (int i = 0; i < hd_num; i++)
            b[1][i] -= ETA_B * d[1][i];
    }  //更新权值和阀值

    public double GetError(int cnt) {    //计算单个样本的误差
        double ans = 0;
        for (int i = 0; i < ou_num; i++)//1
            ans += 0.5 * (x[2][i] - data.elementAt(cnt).y.elementAt(i)) * (x[2][i] - data.elementAt(cnt).y.elementAt(i));
        return ans;
    }

    public double GetAccu() {        //计算所有样本的精度
        double ans = 0;
        int num = data.size();//41
        for (int i = 0; i < num; i++) {
            int m = data.get(i).x.size();//3
            for (int j = 0; j < m; j++)
                x[0][j] = data.elementAt(i).x.elementAt(j);//得到初始值
            ForwardTransfer();//权值w已经更新，得到结果
            int n = data.elementAt(i).y.size();//1
            for (int j = 0; j < n; j++)
                ans += 0.5 * (x[2][j] - data.elementAt(i).y.elementAt(j)) * (x[2][j] - data.elementAt(i).y.elementAt(j));//误差和
        }
        return ans / num;
    }

    public double Sigmoid(double x) {     //计算Sigmoid的值
        return A / (1 + Math.pow(Math.E, (-x / B)));
    }

    public static void main(String[] args) throws IOException {
        double[][] sample =
                {
                        {0, 0, 0, 0},
                        {5, 1, 4, 19.020},
                        {5, 3, 3, 14.150},
                        {5, 5, 2, 14.360},
                        {5, 3, 3, 14.150},
                        {5, 3, 2, 15.390},
                        {5, 3, 2, 15.390},
                        {5, 5, 1, 19.680},
                        {5, 1, 2, 21.060},
                        {5, 3, 3, 14.150},
                        {5, 5, 4, 12.680},
                        {5, 5, 2, 14.360},
                        {5, 1, 3, 19.610},
                        {5, 3, 4, 13.650},
                        {5, 5, 5, 12.430},
                        {5, 1, 4, 19.020},
                        {5, 1, 4, 19.020},
                        {5, 3, 5, 13.390},
                        {5, 5, 4, 12.680},
                        {5, 1, 3, 19.610},
                        {5, 3, 2, 15.390},
                        {1, 3, 1, 11.110},
                        {1, 5, 2, 6.521},
                        {1, 1, 3, 10.190},
                        {1, 3, 4, 6.043},
                        {1, 5, 5, 5.242},
                        {1, 5, 3, 5.724},
                        {1, 1, 4, 9.766},
                        {1, 3, 5, 5.870},
                        {1, 5, 4, 5.406},
                        {1, 1, 3, 10.190},
                        {1, 1, 5, 9.545},
                        {1, 3, 4, 6.043},
                        {1, 5, 3, 5.724},
                        {1, 1, 2, 11.250},
                        {1, 3, 1, 11.110},
                        {1, 3, 3, 6.380},
                        {1, 5, 2, 6.521},
                        {1, 1, 1, 16.000},
                        {1, 3, 2, 7.219},
                        {1, 5, 3, 5.724}
                };

        Vector<Data> data = new Vector<Data>();
        for (int i = 0; i < 41; i++) {
            Data t = new Data();
            for (int j = 0; j < 3; j++)
                t.x.addElement(sample[i][j]);
            t.y.addElement(sample[i][3]);
            data.addElement(t);
        }
        Networks networks = new Networks();
        networks.GetData(data);
        networks.Train();

        Vector<Double> in = new Vector<Double>();
        in.addElement((double) 1);
        in.addElement((double) 3);
        in.addElement((double) 2);
        Vector<Double> ou = networks.ForeCast(in);
        System.out.println("the forecast result of" + in + "is");
        System.out.println(ou.elementAt(0));


    }
}

class Data {
    public Vector<Double> x = new Vector<Double>();
    public Vector<Double> y = new Vector<Double>();

    Data() {
    }
}
