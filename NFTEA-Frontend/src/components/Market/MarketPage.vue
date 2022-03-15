<template>
  <v-container grid-list-md>
    <v-row no-gutters>
      <v-col
          cols="12"
          md="8"
      >
        <Search listings="listings" class="pa-2"></Search>
      </v-col>
      <v-col
          cols="12"
          md="4"
      >
        <v-select
                  class="pa-2"
                  v-model="filter.currentFilter"
                  :items="filter.availableFilters"
                  @change="sortPrice"
                  outlined
                  label="Filter By"
        ></v-select>
      </v-col>
    </v-row>
    <v-row>
    </v-row>
    <v-layout row wrap>
      <v-flex xs12 md4 lg3 v-bind:key="listing.id" v-for="listing in listings">
        <v-hover  v-slot="{ hover }">
          <v-card :img="listing.url" height="400px" :class="{ 'on-hover': hover }">
            <v-card-title v-if="hover">
              {{listing.id}}
            </v-card-title>
            <v-card-actions>
              <v-button v-if="hover">
                Buy
              </v-button>
            </v-card-actions>
          </v-card>
        </v-hover>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
import Search from "./Search";

// Expansion
// Skeleton loader
export default {
  name: "MarketPage",
  components: { Search },
  data: () => ({
    columnCount: 4,
    listings: [
      {
        id: 1,
        url: "https://cdn.vox-cdn.com/thumbor/kPVwAvRiRvewKWEiRqtr4yiN9_M=/1400x788/filters:format(jpeg)/cdn.vox-cdn.com/uploads/chorus_asset/file/23084330/bored_ape_nft_accidental_.jpg",
        price: 10
      },
      {
        id: 2,
        url: "https://www.artnews.com/wp-content/uploads/2022/01/unnamed-2.png?w=631",
        price: 5
      },
      {
        id: 3,
        url: "https://dancingastronaut.com/wp-content/uploads/2022/03/nft-monkey.jpg",
        price: 7
      },
      {
        id: 4,
        url: "https://i.guim.co.uk/img/media/ef8492feb3715ed4de705727d9f513c168a8b196/37_0_1125_675/master/1125.jpg?width=465&quality=45&auto=format&fit=max&dpr=2&s=7ce91813e22e1ca59b2723833dffa49f",
        price: 3
      },
      {
        id: 5,
        url: "https://external-preview.redd.it/UXhb1cR4zPiVTUPZoya4jK0kGQNmUjs_xrrMJ5LIqNM.png?width=640&crop=smart&format=pjpg&auto=webp&s=39c5d65bb3c29ce48c4bf19a0e18cce35fe9745c",
        price: 1
      },
      {
        id: 6,
        url: "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAARcAAAC0CAMAAACJ8pgSAAACWFBMVEUAXtgAXtrY/ATkyKDt3hMEUqcAAADU+geunAwAAAPkyJ4BXdp4d3Lny6IEUqbW/QVJREHV+AQDU6vj/w0AXt/x4hLmzKELAADqy6NubWsGLVra/wDf0ADl1xIAR7QAW9gARpUFT54APYi3kmDYvpmTAABGAABBPzq5ooIFIEGnlHUAABiy3gro/xcAACMAOZ6dlQpTSwu/tQ0AK2qynwwAPJAAUr8AHkrCq4YAFkwEPXUAGUkHRo4ANXBhAABtAAB5AAAwLjajAABVAACGAAAAMGIALGQAO39zWjmNf2YFRIQpJiQ0MzIcAABrX0yUgmlPRzsARqdyXnQAACxOaQiOpAsGVMbA6wiLswadxwexywsAMowAKnlPTht8dADRwgAeL0gAGmBkXws6MwBHSSSOgwAxPEU3MgpHQQ0iIQZoYwx5cA1eXyVARDG5rQ1QRQNmWAA5Nx8XJzFbUEYAFTY4MSWHa0FRPicAFUNEJB81AABpIiNJLDNzaVQmJTWefVFtUVOacXLFfHrBZmm/WFStPUCmXmKRaW6uIiIwDg6mKC4yGgAsFSkiKiiPKCpHGhoqAAlOWmBUOjtpbXV1OztpKytGEg8FGSsiNFBHIyceFA5pVz8mNVArIhOOdDcvQWuVaABhRAZRRVJ4WQA9JwAoICVqUCSLbT0fP0kxS0Q4RgAgLQYGGQJ2hgwoPQAVLAp8igBgbACguwxfhAqvuwyk1ghfag59igXg/FOLkmy5zGGsuG7JyMmPj4/U5n7C2kyVoz+Kkl3U7ltlaUeEjkh/gm2vw0xtlwbxU5cjAAAgAElEQVR4nO2di0NTZ5rGczEnnByTkAshJAoknEASggLhcoyJiJqERExCTTghOmBVRLRl2iKB2k2ZbbezdnadjnVmmIFx3XHX7W4QCkGtnU7XsRf5t/b9vpMLCmiCXUkcXwsNh0s4P5739t3C4xVs/BKzwu9wa7bd91moveaysb3msrG95rKxveaysb3msrG95rKxveaysb3msrG95rKxvSwupUbmNZeN7TWXje01l43tVeDy//GjXwUu3I3wf1JAJc4F/1SKotSc4ccU9VP84JKlwoP7V6traqqr9544afe43W6P/eTe6hq1muLBJ1/0p5cmFx6PUlM11SctPxtsbmsdGtq1e8eOHbt3DbWWDZ7aC2he2KVKlAtY35un24YqsO3gjPugovX0iZoXdqVS5AJ+UtN+/Exrjgi25l0ZPK1n91IvqJiS5KLe+7Pzwzuetl0tmUcVO5rtLyiZkuPC41PV58aGmyvWcalozl2sGDrXz38RMqXGhaJq7MMVzcPrsYCN7M5B2nW2/++IC8Xf+9YQRNYNsVQ0D+auV+x+q/oFFFNKXKAoqXGXQbBt2b0RFogwZ574xM/61VuuY0qKi7r67FDFE3HkKcEMPvGZ3W/XUFt94lLiQlW37Ib73n1mE7nsqGg9v5ZLxZAH9wRb0czL4vITkKH6R4bQ3ZYNbiIXlKqHMkxQcVMxdgLVMVt57tLhwuOdKsO33LJrM7lUVAwPwzvoB4ZasQ2d71fXQFdZvFxelAoUcycGsVyGWirW4QBtoOaorfmd0Zbzpy9cuHBxeLgZbHjw4ltvd9qhm6wpsMsuES5QzfW3tD3tRhjIrtay4YuA4sJwc9kYaiArnjL4iuYL505WF9QZlAgXkIunuQw5UMXFsQoOyY6hseaLp1suDJcNDWVobOpgO3YN9xXiTiXChUf1v9NWhoPqz3dBBGltHmw5fbG5dVdaNM+3iqETryAXijIOt5WNAYKhURDJ6cH0EEMeQDJcTtcU8oQlwQViZs3ZtjYA09zSMjw2tKMgIpwNnSyojnlZXF6IDHxz32DzxZaWwbHd6eRTKJaKwcIyUilw4UET/eb5C2NDu5FMUHUytFnFu6ntsr9qeRrKsr1/GGxrbW1r21Wxe2gMcvEOhKcgNBXD1YU9a1FzQd9XU+1qaW4dKytra90FUFqRZpBiCvOkXX9QF/YrFDUXPr/mxNvDKOCWlbUiNpCEdkOJXyAUNDJT/QrVuxS/793hMQSFM/Ag5EZpB8rfj3YPDZ0rtEUqai4d7+SgAJWhtrZWTinQCxUSeivG9hb63EXMhaKq13IBa0NZOh168zYIRrvOFlTTFTsXPmUffgJMG9xla0YzKF/noxn4urK9xRlctsYFyNT0rsUyNtTaBsUuytLIkXY9j0q2oz5d4GRSsXNBI5drI0wbVHY7sCO14hrmCVEMtY5B4hoeHh4cHLx48cI7Fy5evDg4PNzcNlZ2ssAk/RLRFM6EhwZd1CcG14Bpw4MLWDNPIBkrGx58552RqzMWt8duNxqNtdiM+26XN7a3txs7+wt/9uLkgsbxKaq/o6+jszkHpnUXcqSs+0CfhBYwtLz7Xqfd6KxVYhOhNzClYV+AkUppC1xxFtgEFC8XNO/T57Ho3JZLoxfL0mTATbhyl9PJUGvzxZ+fmtq3p7u8vLx7ar/dWKuUcUxEMpG32yzVCIWaRK1IJPO8Klz4/H6P26CE+3S6PDMtuTwNCWkIvy+7eH4GXGbfuJWWaKRSiYSxBue693lrQTAykfGST6gRSoRCqdUrEhm8Ba+hKk4uPKrf4pQpax1unVunc+0/kw68KA1BMhobbHnPY5QpZZ4QuAqoAkwr1Wi0QiY43m3ptHT7aC3GIpEydpnI2U6hMRy0jqqkufAonsWgNOgcTq/d43Jdcu//OedKEFuGxi6+6zG2G2qdXseeAC3VSoWcSdADCXwoSZgTAEUiEaa5KB19PO5e+/FSqnx+mWLkouZRHr/S4TZ4Jnri8fik5ZJu6gIC0wZQRhr37eucmXifuHygq+uDf2hImKUARKvV0IyZRv4k0fqYEA3AkIYY38SxD3WdPErdYYRwpbNYPB35/WmKkAuEXJ3I7zTMxOuwTU9c0u2BfN023EKQ3b/4x48++vifbnddMZkOma588stfgC9paN/7Bw4c+E0DA2CkvkRlfX3ArJHS45cPmkxXPurm9dt1ToPf4XC7/ZaOfAY0i5OLxyCTeYnpuoxNdrrI5uGf3frnjz7++JMrhw4dOnzYZDrYdfnTT+u7fvXx54nE4SNw0WT6JWmWCrWBetOhQ1d+mbB+esTU1dV1wPSRp9PhdPgNMgjJbrcln2apKLmAXETuSkYioWmGA3PUsedf6j/5xGQ60nXg8OV/tRAm0+H6qw2QoHuv1v/61yZT1+/e/+zAAdMvrwql9KemrgMfHDjy8QdH6q/29r53uf7K+w6X0+CFEO7xi/yO9iLiUggZymMQ6aaskGckQggeCM30pcauA/W/2WNxu8DBZIbLhw7WXy3HtrOqvP5wedXOnTvLjx2+8plVaz1w8HLVzqrxw10NO8sbys9du3ywy+W0hOLT09PxSzKlwZLH6o/i48KjOnRKv7dcgqhotVKJkAYwc42EwyATcTWt0nn40IHfN2AsCAh6g/cNDb85eCSkCRw5XI4vXauqKm9ouHb9j10HT/WkPbLHK1Lq+vL4LYqQC8hFZjdLJVLabLWaaY1UAoIhCD+UsTKoZiFKuOsPHfgUUWm4tjNjgKXqaJepPvHZkctV6UvoK65f//zgQRzBGRoYn5Ip/Ub1c0Nv8XFBcpGJuiWQY3xWxmz1+WgEZpJwiNImc4aOHfoAcWkoX4ulYWcDpJ+DR658nr5WjiR1/Xr5oQPTDGNmGKFWogl4ZAZoC0qQi90vUhp9UjoB5Qgy2mcWWucmCbcszUU5xTSYfv053HRDFgswaBjf2XDEBHblF9yl8YZe8LPrVZ8fqk8kfBM+3zggtu53Gjqfv06+2KhAYwTJSOlhpD6aK2OFWk0gYGWm372U0UvthCbRdei3veW9VVks4yCXqp29mIvpt9y1BsRl5/Wqzw69L9RozD5U5gnNU36Z5fmrwYuNC48y+kEXp4RmKNHSFX4iKJVqmbqjGTdy+TTMMdOvr/aOP+FF4FOzpiP1lfWmD7Ko4EuuVx04koDuQBiAN4mWOe6XdVLPzUhFxgU6Owv0g4ZxqVWI+z7oc8YhBMP/63pqlRyYTqtWGjp45Y+9T2ABuZQfNl1OWMe7Dlalr/X2VkFOMh1GQw4aH4MaBvNxp8H+/Ir3ZXHJlwzV51AqRbUJiVmYbvwCZi33KO5N62XKDB5BgGCqMgYAEIL3jhxMaLX0sSvlCAdcKu/dWVX1e9OEBtomaRBqYWgSpgx+4/MbgWLjonZBCyCDsJvBYvVp8COh1GxPZ6Mps0YiTdQf+m15Jh0fRQx2Xrt86DeQ1Wni4O+groMr5b2o6jtywAouyXERanp1IndfyXHhqT1w6y5jMB1cJJIQ9if0jvZwg3HO/VbolYUTXb/6h2vIYb6tSte9t48cPjoeCiVmf/+rclTRASyQS71pAv8o5EcSKbPfr5zJYxV4kXGBHkAmkumACxdypWakHI6Q5A+Yi8zvTqBAwRBHPjkKuqiqSvcDR4+FglZfIEQQxMHf4ogDmbvqtqme6yg0ASjrNHO1SmdnEfXTeXOxG5RKXa0vrRf8R06PO2luiTAYp2uG8zDChMGMgy4aILrMxntCk1YGek3rxB97uSah6tzBgyEN9kg6oJVIzR746Xm4UbFxgTTthAbGmEjrRRuguUCDuARqufji2p9AA1BaH2H6GEoYTi5VEz2TPdAY9szN+YK+AEHchgBTde2wqZLmAlXQqpXQ3TKRoTOfTQNFxoXPhy5A6fKWayRpLsIsF6mP41Lrckz5NBp0AVzpc5yOj/aSs8REaC4RtJrNgdl4fDoYTAR6r1aajkE6Q99OB8CLEkalyNLHy2NxQ5FRgV/IghRxSpjmkqDTj6C5RneFwDhcrqmQWSjVaK3E4c9mJ0IBX9AM/iNEXYNGKkyE4nXwSKvVCK0TIZTl4ZvBIaVWiNz+/OZMio0Ljw/dtMi9n+FoSK3mTGbSmOe8Ms6RHA6va+r2eCAQCFlpjVaDhiOkKBdzlWAw0QM0aTCNVhoI4qjLWLUaxqJUOi/lt7ShyLjwoLBzwR/VnklIdCLXDpjtoBdIVzK/w+UA0XimElKt0BqYg5CCajaUs9A7ayIO34NCDWo9QzhC0Roh3WtQGi515Hdcwcvikr9iUN9ocJWn5zk0CZrL0lqrmbGnGwEAg8zVIJQyc/E6INATr5No0n0DZJ5prTA4OTk5F5+kNdaglgtR40aZwZJPLipOLnwPZCSDx6zhoorEh71DyyQ05iwXQ63X7/d7glKtta6uh8D2biKQ7h3MIYaZwNdm42at1SeEzE1LE16RE7DU5JkYi48LGvUW1TYIM3ULjbDQAVrKeJSZoQZwJpnIC/U9Mz1NZGw2YEbzaXQwRM9yV+amaU0i4fOZaU3QDrGlI+/p2CLkQnkMSpFzXzAbWKDnYwCLZC0XbCGhVMj0zFVyVEIhAkoUiYQJBgOhoM8XtKKBS2svCslawOLv7M9/HUwxcoESBrqgTqs0Xf+DAnw0aoif4qL0QrbW0gxjtULVYrUKmQkGmkxpIhiqq7MmfFZrMHHOfgrN3vvsIoeHV8DhBEXIhc+3OyHtiCB84IEXiRDcABWshPtJLjKld+poAG7fHEyEKiehomHGgQsdStASpi5+gyA6vUplJ62V+OxKR2dBG0CLkQuvv1OmVCpl3hCjwdPyqFyjE52EW/QUF4PDdYlETlQ5ifIRyl7gRw04ydN10z2dTqXSzpi7jSJ/J6+gPcNFyUXd57l506kU1XrKgwyUIELanPiT0UW4noov0ENCurYQN3rqaClewcCYoZ8MoQE+KHHpuhvQOXhn99dyTlTIRr5i5MKvcf3bv02gWTSZzOix/OncKZ23Vimz5GZKsmaAEm9iuo6WYJcDkdASTTCEV3xIIFJDtBV53TKZzlvAs7/U9ZiFjHzX3PKVf6gzoFGF9PyiyOD0+2cI5zq9KA3+Tiskcilt1qajtDQ4LuXaTEYqOQWZze1A1VxxOlEhvxV184tbb7zRTerQVLRBJjOgBRp+r9trr12nFyh0AsiDNExQiBcDoXGZENdD0D7IQ0aRYU/7Fk5pKD4u6r5/737jjTdmLbyOvnaj1+vyeo1eY3tfv9ptWO9HSgseXoG+kJ6UcjMIzBxXKQtBR3SnUuZSb+EgguLjUlP+Z8Byi+hDh3GoKfSPT6nRAWw62TouSi8u/9BIgqSH5rjQk2mPQgVhwut0FU6lCOMu1f8fSC6VlvQaXnzwBP6D83TrsMhE5yTZ8RnaLOX+P0ln19wJacLtfiXOraA6/gzRZbaxf11aRWXw02Y3SzN9VBaFpMec46IZt7i2sgi/yKig2/+Pzz//z9vrF8Gp2x1KLJEng65EkiOS9p94ZvAGTygQ7i1gKUIu1M0/T3h561MI5XVy61/WeJGO1gjXmXY6kOMilE5cKnzXRFFyQXF2fblB8SEdPckFrQaRrOcirZtc41ZaK7GVw5SKjwsHYf2toLBr8CvXkJGdk0g24sJMMmvCjfDGq8NlI0Pjm35vroSBjtAqXU8FjI77tFn/AsEUuFuttLhQfQ6Z0uVYU9rVjm/kRcim59Y4klDypy2cPPuyuLw4mXY/Gg9fw0XHCDfmIrXmMjX6MLi38GPJSoULpbYboJP05loko0+zCRcN0+Nb62F0Z+GdQKlwQaO+IBgHxwVtv+oWboIFLSGay05r4866wMMZSogLj6dzOkVOp0GWLl081g1Kl6wjxYPaNRGGthd8IlmpcIF05JLJ/NkUXTun2UwtyHPicVq4ZjohVPAZoiVChc/rc7shH2Ur3U5ms2SErW7aqs19Xlp4qi4RLjyq3TEjkmW4iGpDmo1KuqwxddP02s/rCk3VJcPF6yCcIrfTwIVdj/WZcpEIp+uYXIQRagKFnlL8sri8IBme2u4mXEq304laJJly5hlQhHiRWbxuTb4CRyr0CUuDC6Rpr8Uicjn9UNnJlLWJjTuAnF7oibpc5BVKGPurdV5Qjoulz0MYvE6/26mUKe3mZ7oRSkG++HTuaySSbl5hjlQiXHi8mf52wu30O9w6mUw5RT8TCyJBT8ZzXySRFhpgSoQLn9fJ7yAaDQ6nm4ROulv7PC5CaSBuzUZeiTRe/Ur6Ea/Pq6ZmCJdD6agERwo9q6jjTGOeSw9n4jnZeB5790qQC9XRR/EtlTNOmZfQiZxzzw4vGIYm1JP7UBtvL6wVKBEueASvs9LiUDorCUNtSPrMqg6D0QYnUXRG/xgG6aUo48sL52koWHWkziJzVlY6RDeenh3ZgIuQmQxK0zOzVsl/FWl8+SkU49bpdE4DUTmjbKSfrxehcDLBDTZYgwwzWeCRQaXDhU+5nW6HzkASlf7j03gVs4Sbg92EizYwiRfRm30MPX2qwJGGUuKiMzgcFqdlhrQ09tB4MtGs3bSQQUvfb2BZMbSk7ov2Iu0bf4oZgU6D0+nSWXSkZeZ2HV70H7QyVuEGaCQ4IVkJM/6AqaubLHQApnSo8PkWmcEAcnFZdBbLfzE4dgTMTNBszdDIzsRKaLy4lfDRiMr09PT+V+l8zPVc0CbhRofD5XDNTNdhWQQZ6UbD32apUCqliUQw3jMZCiR8fyr0VygWLnn81jWIi8ip8/uBi+5GXZ1EA+U+EoYw011zG/SlWomVppnpHiIUCJohxmg1jLFIz5l9Lplnbn/h1r9Y8JiUw2lAYBrfna5jGJpGZ8SA1aE37nSheM/c5A1Qic9MCzUajVYroc2BfPdLFBuXTT9PoZfQwND6OS5OP96B5CBmexCDyYlQ2iYm0ENwGquZobkDyoBIMBGanJztK/SVC4uDCtz0M7jUICzo6Da0/kVpcMJ7v8OvI4jZiYSZ1kq1Gg3apoZDLudIaF+ORmgOoO0Tk/G6unihabpouGxqFHcWKo/bNoAXeSPZOJwcmKOh8YQvaAWJZIOLVguYaGuAJG7M9XBnVMULdaMS4IIPbeCBN/XpuEUe+Gwcg9+AwUz29NyYraycvXHj6NGJcbzt02xFvtMTn86c28V8YSn4aV8Wly2fNIv2UaGjCimqL7dtAtAYAI0O7Q2Y6MncPzo/Kt7T0xOP504yYyQSa+hk4Stgip0LxJYaqqaj3ev+7/mntgcoZU53I956dGMS0wD74osvpnM6YRirb3zfiRr1q3T+LmeQimra/2dxNBkOJ92y3KpDNF2ilBkcFm5jmr2/ug9be3u73e7R6W7evKmznzyxt7qGO7T2VePCr1EbR9gb84iLS2l4at2hUlTrmEFcdH18NbfWV81Xq/FDCj3c8otRFz8Xyr6oUkTO35m/s37bBDKkmcbFxZl2HsU9TfbUVF7mvyLmsnUy6pOzYkGTKrYwv+dNp0EpWr+nROR0L4aTd2YKHMItcS6UkVCIFQpVU1MskmzcZ6+VydbmJUzGuRhe/DJ2Bi//+Ulexr34/YgCLqwAyAgEqiYVe3d5ceZNe62Bg6PkyMjsxPxCrGn5BEZS8GsolCYXnr1S0aRSiOUCuVyuR2xiS/dHj+9DSxDRljbjm1NnF2IKlUC1vPfviQvVTtiiMZVKIBYLBMAGAKlUCuRT6CRv93v3I7GmJj18tiS5bPUVkOAmPZWr91bBTYCMHMiIkckFTTHy5v/c9I6yTSq9HF9ULXPLC0sqvmxZMZSxEkoXIBOOKJoEYmCDwYhVKWJJH43ONwkEaViqhWrq74VLjbqdiCkiiMzAanIZiQZpRgEUImRyALjYVMi9FAq5WLWU5vJTgClqLug0GAKiRxO7CmRWBqLh5JJNpUdo5E0L8+GV6EA4pUIficUKObtyEm39K608vbXXOuar90WasLFIM8koiOZ+TIVyU9PyPLsyYFsNyzk/0rOrbOTEVrYabSuXrf1yIJcmFRIDkFGkogPLi8mVJMkKEJf7i+x8dCU6j4IxCsMrAwPsMnqt0+cfrlvaXNCBCvYFFWRmhQKF1SYILgpbKhWDdCQWqBaTLHjS6h0W8ncTu5BcXYmyivl9e7f8kvalwIVCjbCa6ieX0olZjNAoVGCohAEUqi/DtsXV6MqdGETchS9XbIBFLE+FI/Mnq6mt7Jguei7w1UCF11998viNUTLJqtJkQCbybAEjUBFhW3hlZSW8AKAUcr2YxfQWU7bo8nvt/Xw17qRrtlrkFR8XJBSe0ruvcjny1elZkiQWFDkyyIWwdASqZJhNhldXIqMKFGDkcq6uuUOSlcnkPNHZzkOHD+d7nFSRc0FZlurvs+8h7kciy5Xk6UgsBne6DG2AXJyp6OQKhKZpaR7lqBUbaYPEDVJCyVpgS8L3na9E476kC+813vpLjRYLFzzA3d/3Jjm6nEotzFYu34Xs3AIfRZJk8i4qdoEHhGAF50gx4h5QWBF/mdIjvaDeSaFaJhdXojZbLHJ/FNB4+vhbDTVFxAUMlDK6kIosjFZeuItaQ4K8n4I0pBhYuXNnyYYSEiKDTaAgo+KV8IA8vKTCJQxYU2x+pJIgydHwQioGbGaJ4+2l7Efos2peR/vUbDKSWq4EKKCUEYIcidhwLAEgbDRMJCMIjZ4LvgLV/KrYdk8MXPAVuapJEQG1rEaW7p8/RpLkfDhlS52uRKeclOR+WPgclBz9xuNEcmHh/uwx7D6nSXJ2OaVIRxREpklhi4bvLC6kWBWw0ctVquWw2GYTi5MRFRp5UMWWFmdBKwSJqprIwv0RYBMGSueP96Hpp5LjgqJKh50YASiVmZhCkMkIK5dnkxAerENsYkvJO/P3l8BLbLYwcLnHAhdFLLV0f35xxcbGEA8UdWeTK9FUZHkEyWYhaS94u9r290egFmUnkfxqYYQ4jaHA3/zMkk0sX4MFg0mjQUNS4fD8l3e+hNKGvcfKw3fmF8OrUZsCgq9eLmZtA8ADCWd0ecA2sJQkieT8qaLVy2ZcKH6fpTK5fJqc/Yptunsa7mdkIaZAEWMNFEwFVykZNixrY1dIG7y3kQOsAvmRgMOHcLK21AIka5JcXB1gBxYgNRkLnS/ZVi5wrb+TmE+OkLPQNn81SpCzAEWO6pG1puDAZE2F4KhUEWIAuAyQ0B1gaumBvAcPFBgNSAXlpmRKzK7eIfoKnHTcVi5Qw12aT46SdyBELEOhej/F6vWZSv9JH1pnqqYUkQIuKeCSvoQGYb77+taHgb9g7SCPSkKwATKK5PF8Dq8uFi583v7REZJYit09T5LnIzaxPveHfyYW1Cg1xbJc0vULRJgHVWT3t7f3fMMJDqFZOAPuFIsm20tnfwBFtSeJ0UjsqxskuRATY6k85UKbqQUXcSTyo+idLBeBQP+/ZPlf//pt+YcPsj0Da4uMAplUY3/JnAOp7r8xTy6DA40u2OQCeXYAO9c9cw30ZlwgvtxjIzkuYrH+L+S1b6u+LW/8S4YLDjULUMmEjYXI5eXVLxtx8RLnI+BAUVau53xjrVoEmyPJcbEhLk3iDBcB0svOv1ZluWDQCrENOqw7/YVEmJfGZb1RHZXHIvNkknMgBTc7lL5BrBO9wLapF3FcxGkueMoNoAoU3xLlf63qvsX5kSKjQFUsTCa9hTjSNnLh24mlJJGCvzrCohCL1wgEFysPJ/bE9JtyUcWIaJqLQJwmA4H3x+NE463/la+pBlVosimanB8tZKvNNnLpJ1si5H19ctUm3vDG//Z149ln+BFwWU1zSfdR+LLiu4cPH2TdEQ2QN8lBL9EImbSXBBfKSNxdJGOC1bDNtkEo0T9861Gj7dlcFsQ47japMhT0YCjWKnJRClpOgQK42EbCi/kfe759XCjensEUmZTrB+Zt6wUjF9gePXr0UKF/ph9hLvPiLBfF48ePHwgUUC9DhZzWjP7uXWi+U1E2UrlQwMtzbxcXNDUUS5IpgZy9c8/GrpdL7NGjHxpvv/XwWVxWkB8hLhwD+XfHr+/58bvAN4+/+/7rbzJcUgsqzIWdXblawCnA28SFz+9siZEjCugP5wcyXNa4k/7ho+9/aGxsPL4eWYZLilgW37NF5gVZLorx7m++3klcv9V9vfshm+ESRlwGWPnyYrg971HN7eJCdRB3F8iIXizQJ6PpALO2iNM/Br28/dbf2I0dSY65rLD3bKtZP5I/+L636vuvb1273V3+4w/HH8pzXJoikPRiZHiq6PTyFBmKMh6LkSQSg35hFQIMqne5wi4NJ4bii2Dj+IJLQFWMRFyQH6Vb6QePv/uwvPzHh7ervvn+60fp0lmVWmHlTav3WL3iTHI0vxcr3z4ufP7xwQi5jO8ysoK5cEjEmRAsf/z9o8YHG9e8uD9AeRpzSdcvKCkrvn/0WKH/y3f6B1VpNwIuqzG9agX0ol8iFvN56emXyuXp5+1ASTqFlmyA0Dm9sCjM5Io7xd++b3xrk9iS5WJbywUP1IDCkMiymRpCbgq4oF4jRi525nsA+jZxobyzMXIU1aNyvW3RZlPIAYuNlctzXOSKh2/3KjYJL5wfRdJcFFksCIyARUEpW8CkohGVYhW4CMRnviTzrXm3hwtFTV0AN1LBzbN6dh4StVxsQ2lJvraS0UN9toleBDhPR5Afof5IkeYC35P64YfuxpQgqxjMhV1dFaNIRhB9eZYw28Sln4jch+JFoGejevkiIsKuJG2oh35OF50xsRz0EgUuUdQfKfBKECQ//Q9vPT5LIP/jrokFsdUIlLurSIgxgsh349o2cYGijiDRKDUb0evDUdDLALnK5k1FjubVoG8EP4qSiiYUr7EPAedHZ2ePfhjB6DJcVkEzURzURwhPUccXtX0kRibRcLUiwrKQqFk2HB7InwvyN6wXmy1FstySTJq2NDcAAAD+SURBVDw4rv/b28Tt7sf69BXEhV1ZVSAuKFgvEPm81uf2ceFNLSyRS4iLajUVWV2xsdFw2CbOl4pAkNPLAME2cd+HRxV+vHb9emZ0Ak/xC9jwKhuJDqCJF3mMaMxzJmlbuEB4SSXJmB70oVpKLUQhUSejK89qntfpRZHRS44LUow+0t199n56NAtzkSvATaOr97gMNULkWdltCxc+hBcodtFfVRWJrQws2qJhnEnz5yLPciFtTYIsFwVayZv9Km5dXjgKYRc6DQRpichzmHdbuKjt8ylyEd+fKgZl3aItDHLZJCdvysWGudie4JKelXyay8AKy9XSMcLz/xh3/w8e+5FmDLmNOgAAAABJRU5ErkJggg==",
        price: 6
      },
    ],
    filter: {
      currentFilter: "",
      availableFilters: ["Price Up", "Price Down"]
    }
  }),
  created() {
    //this.listings = this.getListings();
  },
  methods: {
    async getListings() {
      try {
        // Call API
        this.listings = await this.$http.get('UserProfilePage/getListing/');
        console.log(JSON.stringify(this.listings))
      } catch (e) {
        console.error(e, "Failure to Load Listings.")
      }
    },
    sortPrice() {
      if(this.filter.currentFilter === this.filter.availableFilters[0]) {
        this.listings.sort((a,b) => a.price >= b.price ? 1 : -1);
      } else if(this.filter.currentFilter === this.filter.availableFilters[1]) {
        this.listings.sort((a,b) => a.price <= b.price ? 1 : -1);
      } // Add more filters here if wanted
    }
  },
  beforeMount() {
    let cookies = document.cookie;
    let split = cookies.split(';');
    let log = false;
    for (const element of split) {
      let name = element.split('=')[0];
      if (name === 'id') log = true;
    }
    if (!log) window.location.replace('/');
  }
}
</script>

<style scoped>
.v-card.on-hover {
  opacity: 0.6;
}
</style>