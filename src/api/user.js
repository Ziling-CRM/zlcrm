const user_prefix = '/user'
const admin_prefix = '/admin'

const user = {
    /**
     * user
     */
    addUserProject: {
        url: user_prefix + '/addUserProject',
        type: 'post'
    },
    addProfessionalInfo: {
        url: user_prefix + '/addUserDuty',
        type: 'post'
    },
    addBaseInfo: {
        url: user_prefix + '/addUserBase',
        type: 'post'

    },
    listAllUser: {
        url: user_prefix + '/listAllUser',
        type: 'get'
    },
    getUserInfo: {
        url: user_prefix + '/getUser',
        type: 'get'
    },
    deleteUser: {
        url: user_prefix + '/deleteUser',
        type: 'post'
    },
    updateUserProject: {
        url: user_prefix + '/updateUserProject',
        type: 'post'
    },
    updateUserBase: {
        url: user_prefix + '/updateUserBase', type: 'post'
    },
    updateUserDuty: {
        url: user_prefix + '/updateUserDuty', type: 'post'
    },


    /**
     * admin
     */
    login: {
        url: admin_prefix + '/login',
        type: 'POST'
    },
    listAllAdmin: {
        url: admin_prefix + '/listAllAdmin',
        type: 'get'
    },
    updateAdmin: {
        url: admin_prefix + '/updateAdmin',
        type: 'post'
    },
    deleteAdmin: {
        url: admin_prefix + '/deleteAdmin',
        type: 'post'
    },
    addAdmin: {
        url: admin_prefix + '/addAdmin',
        type: 'post'
    }
}
export default user