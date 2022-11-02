<template>

  <div class="background">

    <div class="options">
      <v-btn class="goAccount">

      </v-btn>

      <v-btn class="goEdit">

      </v-btn>

      <v-btn class="goDelete">

      </v-btn>
    </div>


  </div>


</template>

<script>
export default {
  name: "profile"
}
</script>

<style>
  .background {
    width: 100%;
    height: 100%;
    border-radius: 50px;
    background-color: #F0ECEC;
  }

  .options {
    grid-template-rows: auto;
    position: relative;
    right: -90%;
    top: 2%;
    width: 200px;
    height: 200px;
    border-radius: 18px;
    background-color: #7D4F50;
  }

  .goAccount {
    grid-area: s1;
  }

  .goDelete {
    grid-area: s3;
  }

  .goEdit {
    grid-area: s2;
  }
</style>