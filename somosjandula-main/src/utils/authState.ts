// Estado compartido para evitar dependencias circulares entre main.ts y LoginPage.vue
export let isLoggingIn = false;

export function setLoggingInStatus(status: boolean) {
  isLoggingIn = status;
}
