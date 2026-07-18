class Solution {
    public String minWindow(String s, String t) {
        if (s.length() < t.length()) return "";

        int[] need = new int[128];
        int required = 0;

        for (char c : t.toCharArray()) {
            if (need[c]++ == 0) required++;
        }

        int[] window = new int[128];
        int formed = 0;

        int left = 0;
        int start = 0;
        int minLen = Integer.MAX_VALUE;

        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            window[c]++;

            if (need[c] > 0 && window[c] == need[c])
                formed++;

            while (formed == required) {

                if (right - left + 1 < minLen) {
                    minLen = right - left + 1;
                    start = left;
                }

                char l = s.charAt(left);
                window[l]--;

                if (need[l] > 0 && window[l] < need[l])
                    formed--;

                left++;
            }
        }

        return minLen == Integer.MAX_VALUE ? "" : s.substring(start, start + minLen);
    }
}