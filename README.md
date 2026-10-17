# Nexa Proxy Starter (Android)

Projeto inicial em Kotlin/Android Studio com:
- tela de key (validação demonstrativa; não aceita nenhuma key como válida);
- botão para abrir as opções do desenvolvedor;
- controles de demonstração iniciar/parar;
- sem credenciais embutidas e sem contorno de autenticação.

## Limitações importantes
Este starter **não é uma proxy funcional** e não altera tráfego do Free Fire. O Android não permite que um app comum controle o servidor ADB do próprio aparelho livremente. A Depuração sem fio é gerenciada pelas configurações do Android e por ferramentas ADB autorizadas.

Para uma versão funcional, é necessário definir:
1. O que significa "proxy" no seu caso (VPN local, proxy HTTP/SOCKS, ou comandos ADB).
2. Servidor/backend HTTPS próprio para validar keys.
3. Protocolo e servidor que você controla/autorização para usar.
4. Implementação Android apropriada (por exemplo, `VpnService` para uma VPN local, com consentimento explícito do usuário).

Nunca embuta tokens secretos no APK. A validação de keys deve ocorrer no backend HTTPS.

## Gerar APK no Android Studio
1. Instale Android Studio em um computador e abra esta pasta.
2. Aguarde o Gradle Sync.
3. Se solicitado, instale Android SDK Platform 35 e Build Tools.
4. Para APK de teste: **Build > Build Bundle(s) / APK(s) > Build APK(s)**.
5. O arquivo normalmente ficará em `app/build/outputs/apk/debug/app-debug.apk`.
6. Para distribuição: **Build > Generate Signed Bundle / APK > APK**, crie e guarde sua keystore em local seguro.

## Gerar APK via terminal
Com JDK e Android SDK instalados:
```bash
gradle assembleDebug
```
O APK de depuração será criado em:
`app/build/outputs/apk/debug/app-debug.apk`

Este projeto não inclui o Gradle Wrapper. Use o Gradle do Android Studio ou gere o wrapper localmente (`gradle wrapper`).


## Gerar APK sem instalar Android Studio (GitHub Actions)
1. Crie um repositório no GitHub e envie todos os arquivos desta pasta (incluindo a pasta oculta `.github`).
2. Abra a aba **Actions** do repositório e habilite Actions, se solicitado.
3. Execute **Build Android APK** usando **Run workflow**, ou envie um commit para a branch `main`.
4. Quando terminar, abra a execução concluída e baixe o artefato **NexaProxy-debug-apk**.
5. Extraia o ZIP do artefato; dentro estará `app-debug.apk`.

O APK gerado é de depuração e não é assinado para distribuição na Play Store.
