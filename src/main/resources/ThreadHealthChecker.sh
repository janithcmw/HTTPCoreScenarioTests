

#pid=$(jps | grep Bootstrap | cut -d " " -f1); 
pid=$(cat < Absolute path to wso2 server home > /wso2carbon.pid)
httpthreadcount=$(jstack $pid | grep "HTTP-" | wc -l);
httpParkthreadcount=$(jstack $pid | grep "HTTP-" -A 3 | grep "sun.nio.ch.KQueue.poll" | wc -l;)
Threadpoolishealthy=true;
if [ $httpthreadcount -eq $httpParkthreadcount ]
then
    echo "HTTP thread pool ($httpthreadcount) is healthy"
else
    echo "*********** HTTP pool not healthy ";
    Threadpoolishealthy=false;
fi
httpsthreadcount=$(jstack $pid | grep "HTTPS-" | wc -l;)
httpsParkthreadcount=$(jstack $pid | grep "HTTPS-" -A 3 | grep "sun.nio.ch.KQueue.poll" | wc -l; )
if [ $httpsthreadcount -eq $httpsParkthreadcount ]
then
    echo "HTTPS thread pool ($httpsthreadcount) is healthy"
else
    echo "*********** HTTPS pool not healthy "
    Threadpoolishealthy=false;
fi
PassThroughMessageProcessorthreadcount=$(jstack $pid | grep "PassThroughMessageProcessor" | wc -l; )
PassThroughMessageProcessorParkthreadcount=$(jstack $pid | grep "PassThroughMessageProcessor" -A 3 | grep "jdk.internal.misc.Unsafe.park" | wc -l ;)

if [ $PassThroughMessageProcessorthreadcount -eq $PassThroughMessageProcessorParkthreadcount ]
then
    if jstack $pid | grep -q "discardRequestMessage"
    then
        echo "*********** PassThroughMessageProcessor pool not healthy `jstack $pid | grep "discardRequestMessage" | wc -l` threads are blocked in discardRequestMessage ";
        Threadpoolishealthy=false;
    else
        echo "PassThroughMessageProcessor thread pool ($PassThroughMessageProcessorthreadcount) is healthy";
    fi
else
    echo "*********** PassThroughMessageProcessor pool not healthy ";
    Threadpoolishealthy=false;
fi

# echo "Proto Recv-Q Send-Q  Local Address          Foreign Address        (state)"
echo " Number of active connections for 8243,8253,8100   `netstat -ant | grep -E '8253|8243|8100' | wc -l`";
echo "`netstat -ant | grep -E '8253|8243|8100' `";

# Final check

if [ $Threadpoolishealthy = "true" ]
then
    echo " ";
else
    echo " ********************************* Final result *********************************** ";
    echo " ******************** If you are observing this multiple times ******************** ";
    echo " ************** until the next server restart server is not healthy *************** ";
fi

# echo $httpthreadcount
# echo $httpParkthreadcount
# echo $httpsthreadcount
# echo $httpsParkthreadcount
# echo $PassThroughMessageProcessorthreadcount
# echo $PassThroughMessageProcessorParkthreadcount
