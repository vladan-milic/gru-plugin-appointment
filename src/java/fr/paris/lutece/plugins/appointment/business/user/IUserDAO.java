package fr.paris.lutece.plugins.appointment.business.user;

import java.util.List;

import fr.paris.lutece.portal.service.plugin.Plugin;

/**
 * User DAO Interface
 * 
 * @author Laurent Payen
 *
 */
public interface IUserDAO
{

    /**
     * The name of the bean of the DAO
     */
    String BEAN_NAME = "appointment.userDAO";

    /**
     * Insert a new record in the table.
     * 
     * @param user
     *            instance of the user object to insert
     * @param plugin
     *            the Plugin
     */
    void insert( User user, Plugin plugin );

    /**
     * Update the record in the table
     * 
     * @param user
     *            the reference of the user
     * @param plugin
     *            the Plugin
     */
    void update( User user, Plugin plugin );

    /**
     * Delete a record from the table
     * 
     * @param nIdUser
     *            int identifier of the user to delete
     * @param plugin
     *            the Plugin
     */
    void delete( int nIdUser, Plugin plugin );

    /**
     * Load the data from the table
     * 
     * @param nIdUser
     *            The identifier of the user
     * @param plugin
     *            the Plugin
     * @return The instance of the user
     */
    User select( int nIdUser, Plugin plugin );

    /**
     * Return the users by its email
     * 
     * @param strEmail
     *            the email of the user
     * @param plugin
     *            the plugin
     * @return The Users found
     */
    List<User> findByEmail( String strEmail, Plugin plugin );

    /**
     * Return the user by its first name, last name and email
     * 
     * @param strFirstName
     *            the first name of the user
     * @param strLastName
     *            the last name of the user
     * @param strEmail
     *            the email of the user
     * @param plugin
     *            the plugin
     * @return the user found
     */
    User findByFirstNameLastNameAndEmail( String strFirstName, String strLastName, String strEmail, Plugin plugin );
}
