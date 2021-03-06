package info.minzhou.lightning.rpc.benchmark.netty;


import info.minzhou.lightning.rpc.benchmark.AbstractRPCBenchmarkClient;
import info.minzhou.lightning.rpc.benchmark.BenchmarkTestService;
import info.minzhou.lightning.rpc.netty.client.NettyClientInvocationHandler;

import java.lang.reflect.Proxy;
import java.net.InetSocketAddress;
import java.util.List;
import java.util.Map;

/**
 * Netty RPC Benchmark Client
 *
 */
public class NettyRPCBenchmarkClient extends AbstractRPCBenchmarkClient {

  public static void main(String[] args) throws Exception {
    new NettyRPCBenchmarkClient().run(args);
  }

  public BenchmarkTestService getProxyInstance(
      List<InetSocketAddress> servers, int clientNums,
      int connectTimeout, String targetInstanceName,
      Map<String, Integer> methodTimeouts, int codectype, Integer protocolType) {
    return (BenchmarkTestService) Proxy.newProxyInstance(
        NettyRPCBenchmarkClient.class.getClassLoader(),
        new Class<?>[]{BenchmarkTestService.class},
        new NettyClientInvocationHandler(servers, clientNums,
            connectTimeout, targetInstanceName, methodTimeouts, codectype, protocolType));
  }

}
