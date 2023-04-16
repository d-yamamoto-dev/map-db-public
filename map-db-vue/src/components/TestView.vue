<script setup>
import {ref, reactive} from 'vue';
const count = ref(0);
const state = reactive({
    count:1,
});

const addCount = () => {
    count.value++;
}
const addCount2 = () => {
    state.count++;
}
</script>

<template>
    <div class="test">
        <h2>テスト出力</h2>
        <p>カウント：{{ count }}</p>
        <button @click="addCount">add</button>
        <p>カウント：{{ state.count }}</p>
        <button @click="addCount2">add</button>
    </div>
</template>