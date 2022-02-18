/* eslint-disable */
import $ from 'jquery'

const InstagramAppId = 993784884883159 //process.env.VUE_APP_FACEBOOK_APP_ID;
const InstagramSecret = 'ce77d154d432a11177add2d010469617';
const redirectUri = window.location.origin + '/api-login/';

export default {
    authorize() {
        let url= `https://api.instagram.com/oauth/authorize?client_id=${InstagramAppId}&redirect_uri=${redirectUri}&scope=user_profile,user_media&response_type=code`
        window.open(url, "_blank", null);
        window.close();
    },
    async getToken(code) {
        try {
            let shortIGToken =  await $.ajax({
                url: `https://api.instagram.com/oauth/access_token`,
                type: 'POST',
                data: {
                    client_id: InstagramAppId,
                    client_secret: InstagramSecret,
                    code: code,
                    grant_type: 'authorization_code',
                    redirect_uri: redirectUri
                }
            })

            // TODO: Add shortToken to the login user object (cookies)
            document.cookie = "shortIGToken=" + shortIGToken + "; path=/";
        } catch(e) {
            console.log(e);
        }
    },
    getInstagramContent() {
        // TODO: US010-T02
        // Suggestions:
        //  follow the pattern used in getToken();
        //  use the token from cookies;
        //  reference this page for the data fields: https://developers.facebook.com/docs/instagram-basic-display-api/guides/getting-profiles-and-media
        //  Also, tokens expire every hour, so you may want to check if they are valid, and call authorize otherwise
        let splits = document.cookie.split(';');
        let token = splits[1].split('=')[1];
    }
}