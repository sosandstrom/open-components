/***/
package com.wadpam.open.i18n.json;

import com.wadpam.open.json.JBaseObject;

/**
 * @author sophea <a href='mailto:sm@goldengekko.com'> sophea </a>
 * @version $id$ - $Revision$
 * @date 2013
 */
public class Ji18n extends JBaseObject {

        /** The locale of the localized entity */
        private String            locale;

        /** The parent key this localization belongs to. E.g. a tag or category */
        private Object            parent;

        /** Localized string value */
        private String            localizedString;

        /** Localized image */
        private String            localizedImage;

        /** Localized url */
        private String            localizedUrl;

        
        @Override
        protected String subString() {
            return String.format("locale:%s string:%s", locale, localizedString);
        }


        // Setters and getters
        public String getLocale() {
            return locale;
        }

        public void setLocale(String locale) {
            this.locale = locale;
        }

        public String getLocalizedImage() {
            return localizedImage;
        }

        public void setLocalizedImage(String localizedImage) {
            this.localizedImage = localizedImage;
        }

        public String getLocalizedString() {
            return localizedString;
        }

        public void setLocalizedString(String localizedString) {
            this.localizedString = localizedString;
        }

        public String getLocalizedUrl() {
            return localizedUrl;
        }

        public void setLocalizedUrl(String localizedUrl) {
            this.localizedUrl = localizedUrl;
        }

        public Object getParent() {
            return parent;
        }

        public void setParent(Object parent) {
            this.parent = parent;
        }
}
