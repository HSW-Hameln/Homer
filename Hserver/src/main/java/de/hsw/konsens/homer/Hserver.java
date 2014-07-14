package de.hsw.konsens.homer;

import de.hsw.konsens.homer.webservice.rest.HOMERServer;

import java.util.Map;

/**
 * Created by mielke on 01.04.2014.
 * <p>
 * This Class is the facade for the Hserver framework and contains the main() method for the Standalone service.
 */
public class Hserver {

    /**
     * Singleton
     */
    private static Hserver instance = new Hserver();

    /**
     * Private constructor
     */
    private Hserver() {
    }

    /**
     * @return HOMERServer Singleton.
     */
    public static Hserver getInstance() {
        return instance;
    }

    /**
     * Main method for Hserver Standalone:
     * Commandline arguments -configuration <PATH_TO_FILE>
     */
    public static void main(String[] args) {

        // handle Commandline Arguments
        options o = new options(args);
        //Unknown Argument
        if (o.getUnknown() != null) {
            System.out.print("unknown argument: " + o.getUnknown());
            System.exit(4);
        }
        //Malformed arguments
        if (o.getError() != null) {
            System.out.print("Error: " + o.getError());
            System.exit(4);
        }

        if (o.isWipe())
            //ToDo: wipe data.
            ;

        // Start Server
        HOMERServer.getInstance().start(o.getPort());
    }

    /**
     * @param HOMERQuery A valid Query as String.
     * @return Returns a Map that contains the search results. As well as some information about the expansion and the search process.
     */
    public Map<String, String> search(String HOMERQuery) {
        return new HOMER().search(HOMERQuery);
    }

    private static class options {

        private String unknown = null;
        private boolean wipe = false;
        private int port = 8000;
        private String error;

        options(String[] args) {
            arguments:
            for (int i = 0; i < args.length; i++) {
                switch (args[i].toLowerCase()) {
                    case "-c":
                    case "--clear":
                        setWipe(true);
                        continue arguments;
                    case "-p":
                    case "--port":
                        if (i++ < args.length)
                            try {
                                if (!setPort(Integer.parseInt(args[i])))
                                    break arguments;
                            } catch (NumberFormatException e) {
                                setError("Port needs to be an Integer not: " + args[i]);
                                break arguments;
                            }
                        else {
                            setError("No port after " + args[i - 1]);
                            break arguments;
                        }
                        continue arguments;
                    default:
                        setUnknown(args[i]);
                        break arguments;
                }
            }
        }

        public String getUnknown() {
            return unknown;
        }

        public void setUnknown(String unknown) {
            this.unknown = unknown;
        }

        public boolean isWipe() {
            return wipe;
        }

        public void setWipe(boolean wipe) {
            this.wipe = wipe;
        }

        public boolean setPort(int port) {
            this.port = port;
            if (port > 0 && port < 65536)
                return true;
            setError("Port is out of range");
            return false;
        }

        public int getPort() {
            return port;
        }

        public String getError() {
            return error;
        }

        public void setError(String error) {
            this.error = error;
        }
    }
}