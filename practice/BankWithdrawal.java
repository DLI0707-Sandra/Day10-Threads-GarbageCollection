package practice;

import java.util.Scanner;

class Bank
{
    static int balance;

    static synchronized void withdraw(String name,int amount)
    {
        if(balance>=amount)
        {
            System.out.println(name+" withdrawn "+amount);
            balance-=amount;
            System.out.println("Balance:"+balance);
        }
        else
        {
            System.out.println(name+" cannot withdraw amount:"+amount);
            System.out.println("Balance :"+balance);
        }
    }
}


class WithdrawalThread extends Thread
{
    Bank object;
    String name;
    int amount;

    WithdrawalThread(Bank object,String name,int amount)
    {
        this.object=object;
        this.name=name;
        this.amount=amount;
    }

    public void run()
    {
        object.withdraw(name,amount);
    }
}

public class BankWithdrawal
{
    public static void main(String[] args)
    {
        Scanner scanner=new Scanner(System.in);
        System.out.println("Enter the balance:");
        Bank object=new Bank();
        Bank.balance=scanner.nextInt();


        WithdrawalThread thread1=new WithdrawalThread(object,"Sandra",200);
        WithdrawalThread thread2=new WithdrawalThread(object,"Unnimaya",2000);
        WithdrawalThread thread3=new WithdrawalThread(object,"Swetha",400);
        thread1.start();
        thread2.start();
        thread3.start();
    }
}
