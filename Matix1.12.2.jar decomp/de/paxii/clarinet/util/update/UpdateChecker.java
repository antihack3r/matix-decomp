// 
// Decompiled by Procyon v0.6.0
// 

package de.paxii.clarinet.util.update;

import java.io.InputStream;
import java.net.URL;
import de.paxii.clarinet.Wrapper;
import java.awt.Component;
import javax.swing.JOptionPane;
import de.paxii.clarinet.util.web.JsonFetcher;
import de.paxii.clarinet.Client;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.nio.file.CopyOption;
import java.nio.file.Paths;
import java.io.File;
import de.paxii.clarinet.util.settings.ClientSettings;
import de.paxii.clarinet.util.threads.ThreadChain;

public class UpdateChecker
{
    private static VersionObject latestVersion;
    private static boolean upToDate;
    
    public UpdateChecker() {
        this.checkForUpdates();
    }
    
    private void checkForUpdates() {
        final ThreadChain threadChain = new ThreadChain();
        final File updater = new File(ClientSettings.getClientFolderPath().getValue(), "Updater.jar");
        threadChain.chainRunnable(() -> {
            if (updater.exists()) {
                try {
                    final URL updaterUrl = this.getClass().getClassLoader().getResource("Updater.jar");
                    final File updaterFile = new File(updaterUrl.toURI());
                    if (updaterFile.length() != updater.length()) {
                        updater.delete();
                    }
                }
                catch (final Exception e) {
                    e.printStackTrace();
                }
            }
            if (!updater.exists()) {
                final InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("Updater.jar");
                if (inputStream != null) {
                    try {
                        Files.copy(inputStream, Paths.get(updater.toURI()), StandardCopyOption.REPLACE_EXISTING);
                    }
                    catch (final IOException e2) {
                        e2.printStackTrace();
                    }
                }
            }
            threadChain.next();
            return;
        });
        threadChain.chainRunnable(() -> {
            try {
                final VersionList versionList = JsonFetcher.get(Client.getClientURL() + "versions.json", VersionList.class);
                versionList.getVersions().forEach(versionObject -> {
                    if (versionObject.getGameVersion().equals(Client.getGameVersion()) && versionObject.getClientBuild() > Client.getClientBuild()) {
                        UpdateChecker.latestVersion = versionObject;
                        UpdateChecker.upToDate = false;
                    }
                    return;
                });
            }
            catch (final Exception e3) {
                e3.printStackTrace();
            }
            threadChain.next();
        }).chainRunnable(() -> {
            if (!(!ClientSettings.getValue("client.update", Boolean.class))) {
                if (!isUpToDate()) {
                    final int answer = JOptionPane.showConfirmDialog(null, "There is an Update available, would you like to update?", "Matix Update", 0);
                    if (answer == 0) {
                        try {
                            final String command = String.format("java -jar \"%s\" %s %s \"%s\"", updater.getAbsolutePath(), UpdateChecker.latestVersion.getGameVersion(), UpdateChecker.latestVersion.getUrl(), new File(".").getAbsolutePath());
                            Runtime.getRuntime().exec(command);
                            Wrapper.getMinecraft().shutdown();
                        }
                        catch (final IOException e4) {
                            e4.printStackTrace();
                        }
                    }
                }
                else {
                    System.out.println("Matix is up-to-date");
                }
                threadChain.next();
            }
        }).kickOff();
    }
    
    public static VersionObject getLatestVersion() {
        return UpdateChecker.latestVersion;
    }
    
    public static boolean isUpToDate() {
        return UpdateChecker.upToDate;
    }
    
    static {
        UpdateChecker.upToDate = true;
    }
}
