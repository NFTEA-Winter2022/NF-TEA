/* eslint-disable */
import $ from 'jquery'

const InstagramAppId = 993784884883159 //process.env.VUE_APP_FACEBOOK_APP_ID;
const InstagramSecret = 'ce77d154d432a11177add2d010469617';
const redirectUri = window.location.origin + '/api-login/';
// var redirectUri = window.location.hostname + '/api-login/';
export default {
    authorize() {
        let url= `https://api.instagram.com/oauth/authorize?client_id=${InstagramAppId}&redirect_uri=${redirectUri}&scope=user_profile,user_media&response_type=code`
        window.location.replace(url);
        //window.close();
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
            document.cookie = "shortIGToken=" + JSON.stringify(shortIGToken) + "; path=/";
        } catch(e) {
            console.log(e);
        }
    },
    async getInstagramContent() {
        // TODO: US010-T02
        // Suggestions:
        //  follow the pattern used in getToken();
        //  use the token from cookies;
        //  reference this page for the data fields: https://developers.facebook.com/docs/instagram-basic-display-api/guides/getting-profiles-and-media
        //  Also, tokens expire every hour, so you may want to check if they are valid, and call authorize otherwise

        let token = JSON.parse(this.getCookie("shortIGToken")).access_token;
        let tokenInfo = this.getTokenInfo(token);

        // if(tokenInfo.error === "Invalid OAuth access token."){
        //     this.authorize;
        // } else {
        try { 
            var media = await this.getUserMedia(token);
            return media;
        } catch(e) {
            console.log("BIG ERROR");
            console.log(e);
            }
        // }            
    },
    getCookie(cookieName) {
        let name = cookieName + "=";
        let decodedCookie = decodeURI(document.cookie);
        let cookies = decodedCookie.split(';');
        for(let i = 0; i < cookies.length; i++) {
            let c = cookies[i];
            while (c.charAt(0) == 0) {
                c = c.substring(1);
            }
            if(c.indexOf(name) == 0) {
                return c.substring(name.length, c.length);
            }
        }
        return "";
    },
    async getTokenInfo(token) {
        let tokenInfo;
        try {
            tokenInfo =  await $.ajax({
                url: `https://graph.instagram.com/oauth/access_token_info`,
                type: 'GET',
                data: {
                    access_token: token
                }
            })
        } catch(e) {
            console.log(e);
        }
        return tokenInfo;
    },
    async getUser(token) {
        let fields = "account_type,id,media_count,username"
        try {
            let user =  await $.ajax({
                url: `https://graph.instagram.com/me`,
                type: 'GET',
                data: {
                    fields: fields,
                    access_token: token
                }
            })
        } catch(e) {
            console.log(e);
        }
        return user;
    },
    async getUserMedia(token) {
        var media;
        let fields = "caption, id, media_type, media_url, permalink, thumbnail_url, timestamp, username"; // Some fields might not be needed 
        try {
            media =  await $.ajax({
                url: `https://graph.instagram.com/me/media`,
                type: 'GET',
                data: {
                    fields: fields,
                    access_token: token
                }
            })
            return media;
        } catch(e) {
            console.log(e);
        }
        // return media;
    },
    async getMediaData(mediaID, token) {
        let media;
        let fields = "caption, id, media_type, media_url, permalink, thumbnail_url, timestamp, username"; // Some fields might not be needed 
        try {
            media =  await $.ajax({
                url: `https://graph.instagram.com/${mediaID}`,
                type: 'GET',
                data: {
                    fields: fields,
                    access_token: token
                }
            })
        } catch(e) {
            console.log(e);
        }
        return media;
    },
    async getAlbumContent(mediaID, token) {
        let albumContent;
        let fields = "caption, id, media_type, media_url, permalink, thumbnail_url, timestamp, username"; // Some fields might not be needed 
        try {
            albumContent =  await $.ajax({
                url: `https://graph.instagram.com/${mediaID}/children`,
                type: 'GET',
                data: {
                    fields: fields,
                    access_token: token
                }
            })
        } catch(e) {
            console.log(e);
        }
        return albumContent;
    }
}