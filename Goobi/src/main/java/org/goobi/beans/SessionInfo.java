/**
 * This file is part of the Goobi Application - a Workflow tool for the support of mass digitization.
 * 
 * Visit the websites for more information.
 *             - https://goobi.io
 *             - https://www.intranda.com
 * 
 * This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free
 * Software Foundation; either version 2 of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along with this program; if not, write to the Free Software Foundation, Inc., 59
 * Temple Place, Suite 330, Boston, MA 02111-1307 USA
 * 
 * Linking this library statically or dynamically with other modules is making a combined work based on this library. Thus, the terms and conditions
 * of the GNU General Public License cover the whole combination. As a special exception, the copyright holders of this library give you permission to
 * link this library with independent modules to produce an executable, regardless of the license terms of these independent modules, and to copy and
 * distribute the resulting executable under terms of your choice, provided that you also meet, for each linked independent module, the terms and
 * conditions of the license of that module. An independent module is a module which is not derived from or based on this library. If you modify this
 * library, you may extend this exception to your version of the library, but you are not obliged to do so. If you do not wish to do so, delete this
 * exception statement from your version.
 */
package org.goobi.beans;


import java.io.Serializable;

import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;
/**
 * This class represents a HTTPSession object and some more information about
 * a current session of a user. It is mainly used in the SessionForm class.
 *
 * @author Maurice Mueller
 *
 */
public class SessionInfo implements Serializable {

    /**
     * The version id for serializing processes
     */
    private static final long serialVersionUID = 8457947420232054227L;

    /**
     * The HTTP session object
     */
    @Getter
    @JsonIgnore
    @Setter
    private HttpSession session;

    /**
     * The id of the HTTP session object
     */
    @Getter
    @JsonIgnore
    @Setter
    private String sessionId;

    /**
     * The time stamp when the session was created (in milliseconds)
     */
    @Getter
    @JsonIgnore
    @Setter
    private long sessionCreatedTimestamp;

    /**
     * The time stamp when the session was created (formatted)
     */
    @Getter
    @Setter
    private String sessionCreatedFormatted;

    /**
     * The time stamp of the last access of the user in the GUI (in milliseconds)
     */
    @Getter
    @JsonIgnore
    @Setter
    private long lastAccessTimestamp;

    /**
     * The time stamp of the last access of the user in the GUI (formatted)
     */
    @Getter
    @Setter
    private String lastAccessFormatted;

    /**
     * The user id
     */
    @Getter
    @JsonIgnore
    @Setter
    private int userId;

    /**
     * The IP address of the user
     */
    @Getter
    @Setter
    private String userIpAddress;

    /**
     * The name of the user, represented by the last name and the first name
     */
    @Getter
    @Setter
    private String userName;

    /**
     * The timeout of the user in seconds
     */
    @Getter
    @JsonIgnore
    @Setter
    private int userTimeout;

    /**
     * The name of the browser of the user
     */
    @Getter
    @Setter
    private String browserName;

    /**
     * The file name of the fitting browser icon image
     */
    @Getter
    @JsonIgnore
    @Setter
    private String browserIconFileName;

    /**
     * A constructor to get a SessionInfo object.
     * All object variables can be set separately.
     */
    public SessionInfo() {
    }
}