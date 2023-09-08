/**
 * This file is part of the Goobi Application - a Workflow tool for the support of mass digitization.
 * 
 * Visit the websites for more information.
 *          - https://goobi.io
 *          - https://www.intranda.com
 *          - https://github.com/intranda/goobi-workflow
 * 
 * This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free
 * Software Foundation; either version 2 of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along with this program; if not, write to the Free Software Foundation, Inc., 59
 * Temple Place, Suite 330, Boston, MA 02111-1307 USA
 */
package io.goobi.workflow.harvester.repository.oai.viewer;

import java.sql.Timestamp;

public class IntrandaViewerCrowdsourcingRepository extends IntrandaViewerRepository {

    public static final String TYPE = "IV_CROWDSOURCING";
    private static final String METADATA_PREFIX = "iv_crowdsourcing";

    public IntrandaViewerCrowdsourcingRepository() {
        parameter.put("metadataPrefix", METADATA_PREFIX);
    }

    /**
     * @param id {@link String}
     * @param name
     * @param url {@link String}
     * @param scriptPath
     * @param lastHarvest {@link Timestamp}
     * @param frequency int
     * @param delay int
     * @param enabled int
     */
    public IntrandaViewerCrowdsourcingRepository(String id, String name, String url, String scriptPath, Timestamp lastHarvest, int frequency,
            int delay, boolean enabled) {
        super(id, name, url, scriptPath, lastHarvest, frequency, delay, enabled);
        parameter.put("metadataPrefix", METADATA_PREFIX);
    }

    @Override
    public String getType() {
        return TYPE;
    }
}
