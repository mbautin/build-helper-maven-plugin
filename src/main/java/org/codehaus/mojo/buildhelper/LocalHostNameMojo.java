package org.codehaus.mojo.buildhelper;

/*
 * The MIT License
 *
 * Copyright (c) 2004, The Codehaus
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies
 * of the Software, and to permit persons to whom the Software is furnished to do
 * so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

/**
 * Retrieve current hostname and place it under a configurable project property
 * 
 * @author <a href="kama@soebes.de">Karl-Heinz Marbaise</a>
 * @since 1.9
 */
@Mojo( name = "local-hostname", defaultPhase = LifecyclePhase.PROCESS_TEST_CLASSES, threadSafe = true )
public class LocalHostNameMojo
    extends AbstractDefinePropertyMojo
{

    /**
     * The name of the property in which to store the local hostname.
     */
    @Parameter( defaultValue = "local.hostname" )
    private String localHostNameProperty;

    /**
     * to be called from Maven.
     */
    public void execute()
        throws MojoExecutionException
    {
        try
        {
            defineProperty( this.localHostNameProperty, InetAddress.getLocalHost().getHostName() );
        }
        catch ( UnknownHostException e )
        {
            throw new MojoExecutionException( "Unable to retrieve localhost hostname.", e );
        }
    }
}