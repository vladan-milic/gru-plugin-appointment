/*
 * Copyright (c) 2002-2018, Mairie de Paris
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  1. Redistributions of source code must retain the above copyright notice
 *     and the following disclaimer.
 *
 *  2. Redistributions in binary form must reproduce the above copyright notice
 *     and the following disclaimer in the documentation and/or other materials
 *     provided with the distribution.
 *
 *  3. Neither the name of 'Mairie de Paris' nor 'Lutece' nor the names of its
 *     contributors may be used to endorse or promote products derived from
 *     this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDERS OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *
 * License 1.0
 */
package fr.paris.lutece.plugins.appointment.business.appointment;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.paris.lutece.plugins.appointment.business.UtilDAO;
import fr.paris.lutece.portal.service.plugin.Plugin;
import fr.paris.lutece.util.sql.DAOUtil;

/**
 * Appointment Response DAO
 * 
 * @author Laurent Payen
 *
 */
public final class AppointmentResponseDAO extends UtilDAO implements IAppointmentResponseDAO
{

    private static final String SQL_QUERY_NEW_PK = "SELECT max(id_appointment_response) FROM appointment_appointment_response";
    private static final String SQL_QUERY_INSERT_APPOINTMENT_RESPONSE = "INSERT INTO appointment_appointment_response ( id_appointment, id_response) VALUES (?,?)";
    private static final String SQL_QUERY_REMOVE_FROM_ID_RESPONSE = "DELETE FROM appointment_appointment_response WHERE id_response = ?";
    private static final String SQL_QUERY_SELECT_APPOINTMENT_RESPONSE_LIST = "SELECT id_response FROM appointment_appointment_response WHERE id_appointment = ?";

    @Override
    public void insertAppointmentResponse( int nIdAppointment, int nIdResponse, Plugin plugin )
    {
        int nIndex = 1;
        DAOUtil daoUtil = null;
        try
        {
            daoUtil = new DAOUtil( SQL_QUERY_INSERT_APPOINTMENT_RESPONSE, Statement.RETURN_GENERATED_KEYS, plugin );
            daoUtil.setInt( nIndex++, nIdAppointment );
            daoUtil.setInt( nIndex++, nIdResponse );
            daoUtil.executeUpdate( );
        }
        finally
        {
            if ( daoUtil != null )
            {
                daoUtil.free( );
            }
        }
    }

    @Override
    public void removeAppointmentResponseByIdResponse( int nIdResponse, Plugin plugin )
    {
        DAOUtil daoUtil = null;
        try
        {
            daoUtil = new DAOUtil( SQL_QUERY_REMOVE_FROM_ID_RESPONSE, plugin );
            daoUtil.setInt( 1, nIdResponse );
            daoUtil.executeUpdate( );
        }
        finally
        {
            if ( daoUtil != null )
            {
                daoUtil.free( );
            }
        }
    }

    @Override
    public List<Integer> findListIdResponse( int nIdAppointment, Plugin plugin )
    {
        DAOUtil daoUtil = null;
        List<Integer> listIdResponse = new ArrayList<Integer>( );
        try
        {
            daoUtil = new DAOUtil( SQL_QUERY_SELECT_APPOINTMENT_RESPONSE_LIST, plugin );
            daoUtil.setInt( 1, nIdAppointment );
            daoUtil.executeQuery( );
            while ( daoUtil.next( ) )
            {
                listIdResponse.add( daoUtil.getInt( 1 ) );
            }
        }
        finally
        {
            if ( daoUtil != null )
            {
                daoUtil.free( );
            }
        }
        return listIdResponse;
    }

}
