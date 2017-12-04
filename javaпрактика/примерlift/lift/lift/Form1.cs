using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace lift
{
    public partial class Form1 : Form
    {
        int m, s, h;
        public Form1()
        {
            InitializeComponent();

            // минуты, секунды, миллисекунды
            

            // настройка таймера:
            // сигнал  - каждую секунду
            timer1.Interval = 1000;

            // обнуление показаний
            m = 0; s = 0; h = 0;
        }

        private void button1_Click(object sender, EventArgs e)
        {            
                for (int q = label1.Location.Y; q <= 240; q++)
                { 
                    Point UP = new Point(label1.Location.X, q);
                    label1.Location = UP;
                    Point UP2 = new Point(label2.Location.X, q);
                    label2.Location = UP2;
                    Point UP4 = new Point(label4.Location.X, q);
                    label4.Location = UP4;
                    System.Threading.Thread.Sleep(10);
                }
                Point Left = new Point(108, label2.Location.Y);
                label2.Location = Left;
                Point Right = new Point(180, label4.Location.Y);
                label4.Location = Right;
                s = 0;
        }

        private void button2_Click(object sender, EventArgs e)
        {
            if (label1.Location.Y < 218)
            {
                for (int q = label1.Location.Y; q <= 218; q++)
                {
                    Point Down = new Point(label1.Location.X, q);
                    label1.Location = Down;
                    Point Down2 = new Point(label2.Location.X, q);
                    label2.Location = Down2;
                    Point Down4 = new Point(label4.Location.X, q);
                    label4.Location = Down4;
                    System.Threading.Thread.Sleep(10);
                }
            }
            else
            {
                for (int q = label1.Location.Y; q >= 218; q--)
                {
                    Point Down = new Point(label1.Location.X, q);
                    label1.Location = Down;
                    Point Down2 = new Point(label2.Location.X, q);
                    label2.Location = Down2;
                    Point Down4 = new Point(label4.Location.X, q);
                    label4.Location = Down4;
                    System.Threading.Thread.Sleep(10);
                }
            }
            Point Left = new Point(108, label2.Location.Y);
            label2.Location = Left;
            Point Right = new Point(180, label4.Location.Y);
            label4.Location = Right;
            s = 0;
        }

        private void Form1_Load(object sender, EventArgs e)
        {

        }

        private void button3_Click(object sender, EventArgs e)
        {
            if (label1.Location.Y < 155)
            {
                for (int q = label1.Location.Y; q <= 155; q++)
                {
                    Point Down = new Point(label1.Location.X, q);
                    label1.Location = Down;
                    Point Down2 = new Point(label2.Location.X, q);
                    label2.Location = Down2;
                    Point Down4 = new Point(label4.Location.X, q);
                    label4.Location = Down4;
                    System.Threading.Thread.Sleep(10);
                }
            }
            else
            {
                for (int q = label1.Location.Y; q >= 155; q--)
                {
                    Point Down = new Point(label1.Location.X, q);
                    label1.Location = Down;
                    Point Down2 = new Point(label2.Location.X, q);
                    label2.Location = Down2;
                    Point Down4 = new Point(label4.Location.X, q);
                    label4.Location = Down4;
                    System.Threading.Thread.Sleep(10);
                }
            }
            Point Left = new Point(108, label2.Location.Y);
            label2.Location = Left;
            Point Right = new Point(180, label4.Location.Y);
            label4.Location = Right;
            s = 0;
        }

        private void button4_Click(object sender, EventArgs e)
        {
            if (label1.Location.Y < 92)
            {
                for (int q = label1.Location.Y; q <= 92; q++)
                { 
                Point Down = new Point(label1.Location.X, q);
                label1.Location = Down;
                Point Down2 = new Point(label2.Location.X, q);
                label2.Location = Down2;
                Point Down4 = new Point(label4.Location.X, q);
                label4.Location = Down4;
                System.Threading.Thread.Sleep(10);
                }
            }
                else
                {
                     for (int q = label1.Location.Y; q >= 92; q--)
                { 
                Point Down = new Point(label1.Location.X, q);
                label1.Location = Down;
                Point Down2 = new Point(label2.Location.X, q);
                label2.Location = Down2;
                Point Down4 = new Point(label4.Location.X, q);
                label4.Location = Down4;
                System.Threading.Thread.Sleep(10);
                }
                }
            Point Left = new Point(108, label2.Location.Y);
            label2.Location = Left;
            Point Right = new Point(180, label4.Location.Y);
            label4.Location = Right;
            s = 0;
            
        }

        private void button5_Click(object sender, EventArgs e)
        {
                for (int q = label1.Location.Y; q >= 28; q--)
                { 
                Point Down = new Point(label1.Location.X, q);
                label1.Location = Down;
                Point Down2 = new Point(label2.Location.X, q);
                label2.Location = Down2;
                Point Down4 = new Point(label4.Location.X, q);
                label4.Location = Down4;
                System.Threading.Thread.Sleep(10);                
            }
                Point Left = new Point(108, label2.Location.Y);
                label2.Location = Left;
                Point Right = new Point(180, label4.Location.Y);
                label4.Location = Right;
                s = 0;
        }

     

        private void button6_Click(object sender, EventArgs e)
        {
            s = 0;
            Point Left = new Point(108, label2.Location.Y);
            label2.Location = Left;
            Point Right = new Point(180, label4.Location.Y);
            label4.Location = Right;
        }

        private void label4_Click(object sender, EventArgs e)
        {

        }

        private void label2_Click(object sender, EventArgs e)
        {

        }

        private void timer1_Tick(object sender, EventArgs e)
        {
           
            if (label2.Location == new Point(108, label2.Location.Y))
            {
                
                s++;
                if (s >= 4)
                {
                    Point Left = new Point(128, label2.Location.Y);
                    label2.Location = Left;
                    Point Right = new Point(160, label4.Location.Y);
                    label4.Location = Right;
                }
            }
        }

    }
}
