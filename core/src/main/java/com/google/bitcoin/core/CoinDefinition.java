package com.google.bitcoin.core;

import java.math.BigInteger;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: HashEngineering
 * Date: 5/19/2014
 * Time: 7:23 PM
 * To change this template use File | Settings | File Templates.
 */
public class CoinDefinition {


    public static final String coinName = "omnicoin";
    public static final String coinTicker = "OMC";
    public static final String coinURIScheme = "omnicoin";
    public static final String cryptsyMarketId = "672";
    public static final String cryptsyMarketCurrency = "BTC";
    public static final String PATTERN_PRIVATE_KEY_START = "d";

    public static String lowerCaseCoinName() { return coinName.toLowerCase(); }

    public enum CoinPrecision {
        Coins,
        Millicoins,
    }
    public static final CoinPrecision coinPrecision = CoinPrecision.Coins;


    public static final String BLOCKEXPLORER_BASE_URL_PROD = "https://omnicha.in/";    //blockr.io
    public static final String BLOCKEXPLORER_ADDRESS_PATH = "?address=";             //blockr.io path
    public static final String BLOCKEXPLORER_TRANSACTION_PATH = "?transaction=";              //blockr.io path
    public static final String BLOCKEXPLORER_BLOCK_PATH = "?block=";                 //blockr.io path
    public static final String BLOCKEXPLORER_BASE_URL_TEST = BLOCKEXPLORER_BASE_URL_PROD;

    public static final String DONATION_ADDRESS = "oMeshU9A7psVWNajjzdogeaRmY2xaGMuRA";  //HashEngineering donation DGC address

    enum CoinHash {
        SHA256,
        scrypt,
    };
    public static final CoinHash coinPOWHash = CoinHash.scrypt;

    public static boolean checkpointFileSupport = true;
    //Original Values
    public static final int TARGET_TIMESPAN_0 = (int)(6 * 60 * 3 * 20);  // 3.5 days per difficulty cycle, on average.
    public static final int TARGET_SPACING_0 = (int)(3 * 60);  // 180 seconds per block.
    public static final int INTERVAL_0 = TARGET_TIMESPAN_0 / TARGET_SPACING_0;  //1080 blocks

    public static final int TARGET_TIMESPAN_1 = (int)(108 * 20);  // 36 minutes per difficulty cycle, on average.
    public static final int TARGET_SPACING_1 = (int)(3 * 60);  // 180 seconds per block.
    public static final int INTERVAL_1 = TARGET_TIMESPAN_1 / TARGET_SPACING_1;  //108 blocks

    public static final int TARGET_TIMESPAN = (int)(4 * 60 * 40);  // 4 hourse per difficulty cycle, on average.
    public static final int TARGET_SPACING = (int)(3 * 60);  // 180 seconds per block.
    public static final int INTERVAL = TARGET_TIMESPAN / TARGET_SPACING;  //108 blocks

    private static int nDifficultySwitchHeight = 476280;    //retarget every 108 instead of 1080 blocks; adjust by +100%/-50% instead of +400/-75%
    private static int nInflationFixHeight = 523800;        //increase block time to 40 from 20 seconds; decrease reward from 20 to 15 DGC
    private static int nDifficultySwitchHeightTwo = 625800; //retarget adjust changed



    public static final int getInterval(int height, boolean testNet) {
        if(height < nDifficultySwitchHeight)
            return INTERVAL_0;    //1080
        else if(height < nInflationFixHeight)
            return INTERVAL_1;    //108
        else
            return INTERVAL;      //108
    }
    public static final int getIntervalCheckpoints() {
            return INTERVAL_0 *2;    //1080

    }
    public static final int getTargetTimespan(int height, boolean testNet) {
        if(height < nDifficultySwitchHeight)
            return TARGET_TIMESPAN_0;  //3.5 days
        else if(height < nInflationFixHeight)
            return TARGET_TIMESPAN_1;  //36 min
        else
            return TARGET_TIMESPAN;    //72 min
    }
    public static int getMaxTimeSpan(int value, int height, boolean testNet)
    {
        if(height < nDifficultySwitchHeight)
            return value * 4;
        else if(height < nInflationFixHeight)
            return value * 2;
        else
            return value * 75 / 60;
    }
    public static int getMinTimeSpan(int value, int height, boolean testNet)
    {
        if(height < nDifficultySwitchHeight)
            return value / 4;
        else if(height < nInflationFixHeight)
            return value / 2;
        else
            return value * 55 / 73;
    }
    public static int spendableCoinbaseDepth = 40; //main.h: static const int COINBASE_MATURITY
    public static final BigInteger MAX_MONEY = BigInteger.valueOf(13371337).multiply(Utils.COIN);                 //main.h:  MAX_MONEY
    //public static final String MAX_MONEY_STRING = "13371337";     //main.h:  MAX_MONEY

    public static final BigInteger DEFAULT_MIN_TX_FEE = BigInteger.valueOf(100000);   // MIN_TX_FEE
    public static final BigInteger DUST_LIMIT = Utils.CENT; //main.h CTransaction::GetMinFee        0.01 coins

    public static final int PROTOCOL_VERSION = 80007;          //version.h PROTOCOL_VERSION
    public static final int MIN_PROTOCOL_VERSION = 80004;        //version.h MIN_PROTO_VERSION - eliminate 60001 which are on the wrong fork

    public static final int BLOCK_CURRENTVERSION = 1;   //CBlock::CURRENT_VERSION
    public static final int MAX_BLOCK_SIZE = 1 * 1000 * 1000;


    public static final boolean supportsBloomFiltering = false; //Requires PROTOCOL_VERSION 70000 in the client
    public static boolean supportsIrcDiscovery() {
        return PROTOCOL_VERSION <= 70000;
    }

    public static final int Port    = 4872;       //protocol.h GetDefaultPort(testnet=false)
    public static final int TestPort = 14872;     //protocol.h GetDefaultPort(testnet=true)

    //
    //  Production
    //
    public static final int AddressHeader = 115;             //base58.h CBitcoinAddress::PUBKEY_ADDRESS
    public static final int p2shHeader = 5;             //base58.h CBitcoinAddress::SCRIPT_ADDRESS
    public static final boolean allowBitcoinPrivateKey = true; //for backward compatibility with previous version of digitalcoin
    public static final long PacketMagic = 0xd4cba1ef;      //0xd4, 0xcb, 0xa1, 0xef

    //Genesis Block Information from main.cpp: LoadBlockIndex
    static public long genesisBlockDifficultyTarget = (0x1e0ffff0L);         //main.cpp: LoadBlockIndex
    static public long genesisBlockTime = 1388880557L;                       //main.cpp: LoadBlockIndex
    static public long genesisBlockNonce = (751211697);                         //main.cpp: LoadBlockIndex
    static public String genesisHash = "721abe3814e15f1ab50514c8b7fffa7578c1f35aa915275ee91f4cb8d02be5c4"; //main.cpp: hashGenesisBlock
    static public int genesisBlockValue = 88;                                                              //main.cpp: LoadBlockIndex
    //taken from the raw data of the block explorer
    static public String genesisTxInBytes = "35e6a0e897ed76cd5f08b75d118fb7c99aec7cdd297b96c21dc6671d2034c953";   //"Digitalcoin, A Currency for a Digital Age"
    static public String genesisTxOutBytes = "040184710fa689ad5023690c80f3a49c8f13f8d45b8c857fbcbc8bc4a8e4d3eb4b10f4d4604fa08dce601aaf0f470216fe1b51850b4acf21b179c45070ac7b03a9";

    //net.cpp strDNSSeed
    static public String[] dnsSeeds = new String[] {
            "192.99.42.206",
        "192.99.42.164",
        "192.99.15.174",
		"198.100.155.225",
		"192.99.4.195",
		"50.197.11.193",
		"184.56.132.89",
		"92.222.29.169",
		"192.99.42.54",
    };

    public static int minBroadcastConnections = 1;   //0 for default; we need more peers.

    //
    // TestNet - digitalcoin - not tested
    //
    public static final boolean supportsTestNet = false;
    public static final int testnetAddressHeader = 111;             //base58.h CBitcoinAddress::PUBKEY_ADDRESS_TEST
    public static final int testnetp2shHeader = 196;             //base58.h CBitcoinAddress::SCRIPT_ADDRESS_TEST
    public static final long testnetPacketMagic = 0xfcc1b7dc;      //0xfc, 0xc1, 0xb7, 0xdc
    public static final String testnetGenesisHash = "721abe3814e15f1ab50514c8b7fffa7578c1f35aa915275ee91f4cb8d02be5c4";
    static public long testnetGenesisBlockDifficultyTarget = (0x1e0ffff0L);         //main.cpp: LoadBlockIndex
    static public long testnetGenesisBlockTime = 999999L;                       //main.cpp: LoadBlockIndex
    static public long testnetGenesisBlockNonce = (99999);                         //main.cpp: LoadBlockIndex


	public static BigInteger startDifficulty = Utils.decodeCompactBits(0x1d3fffff);


    public static final boolean usingNewDifficultyProtocol(int height)
    { return height >= nDifficultySwitchHeight;}

    public static final boolean usingInflationFixProtocol(int height)
    { return height >= nInflationFixHeight;}

    //main.cpp GetBlockValue(height, fee)
    public static final BigInteger GetBlockReward(int height)
    {
        int COIN = 1;
        BigInteger nSubsidy = Utils.toNanoCoins(15, 0);

        if(height < 1080)
        {
            nSubsidy = Utils.toNanoCoins(2, 0); //2
        }
        else if(height < 2160)
        {
            nSubsidy   = Utils.toNanoCoins(1, 0); //2
        }
        else if(height < 3240)
        {
            nSubsidy   = Utils.toNanoCoins(2, 0); //2
        }
        else if(height < 4320)
        {
            nSubsidy  = Utils.toNanoCoins(5, 0); //5
        }
        else if(height < 5400)
        {
            nSubsidy  = Utils.toNanoCoins(8, 0); //8
        }
        else if(height < 6480)
        {
            nSubsidy = Utils.toNanoCoins(11, 0); //11
        }
        else if(height < 7560)
        {
            nSubsidy  = Utils.toNanoCoins(14, 0); //14
        }
        else if(height < 8640)
        {
            nSubsidy = Utils.toNanoCoins(17, 0); //17
        }
        else if(height < 523800)
        {
            nSubsidy = Utils.toNanoCoins(20, 0); //2
        }
        else
        {
            return nSubsidy.shiftRight(height / subsidyDecreaseBlockCount);
        }
        return nSubsidy;
    }

    public static int subsidyDecreaseBlockCount = 100010;     //main.cpp GetBlockValue(height, fee)

    public static BigInteger proofOfWorkLimit = Utils.decodeCompactBits(0x1e0fffffL);  //main.cpp bnProofOfWorkLimit (~uint256(0) >> 20); // digitalcoin: starting difficulty is 1 / 2^12

    static public String[] testnetDnsSeeds = new String[] {
          "not supported"
    };
    //from main.h: CAlert::CheckSignature
    public static final String SATOSHI_KEY = "040182710fa6892d5023690c80f3a49c8f13f8d45b8c857fbcbc8bc4a8e4d3eb4b10f4d4604fa08dce601aaf0f470216fe1b51850b4acf21b179c45070ac7b03a9";
    public static final String TESTNET_SATOSHI_KEY = "04302290343f912c401d56d68b123028bf52e5fca1939df127f63c6467cdf9c8e2c14b61104cf817d0b780da337893ecc4aaff1309e536162dabbdb45200ca2b0a";

    /** The string returned by getId() for the main, production network where people trade things. */
    public static final String ID_MAINNET = "org.omnicoin.production";
    /** The string returned by getId() for the testnet. */
    public static final String ID_TESTNET = "org.omnicoin.test";
    /** Unit test network. */
    public static final String ID_UNITTESTNET = "com.google.omnicoin.unittest";

    //checkpoints.cpp Checkpoints::mapCheckpoints
    public static void initCheckpoints(Map<Integer, Sha256Hash> checkpoints)
    {
checkpoints.put( 0, new Sha256Hash("721abe3814e15f1ab50514c8b7fffa7578c1f35aa915275ee91f4cb8d02be5c4"));
        checkpoints.put( 1, new Sha256Hash("e4a260c77abf60959a6be3f2058039fe12cb9be924b4be9fe12c95d9133b73ec"));
        checkpoints.put( 20, new Sha256Hash("b83b8d949773f345b71f3380d5ac21e20d845a92502abb6a18a1d0f2ebf97ef3"));
        checkpoints.put( 3500, new Sha256Hash("9197cc7b26e68454de896d174ea10e604b83e060499cd340c3ef38a03de220a6"));
        checkpoints.put( 22222, new Sha256Hash("df1092e6465272939beb25a4550934a690c15ac8c5e3075a967e0860e73974d0"));
        checkpoints.put( 60580, new Sha256Hash("7c81dd9e1b6155f6c7dadf4d245539016fc71bbf3a522f069117ce6c64f47f2b"));
    }

    //Unit Test Information
    public static final String UNITTEST_ADDRESS = "oTestfQJXaWsR2tpe7GXE5v3yuUsjT4kFd";
    public static final String UNITTEST_ADDRESS_PRIVATE_KEY = "99mRTUkhViY7meCiZ7DCfgH3A3FW2XxtLn1mviPZB6wYRWMz8dS";

}
