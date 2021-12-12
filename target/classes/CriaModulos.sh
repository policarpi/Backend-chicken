##Criar os mudulos e e componnets.
#!/bin/zsh

cd src/app/
mkdir alimentos
cd alimentos
ng generate module alimentos --routing
ng generate component  alimentos-forms  --skipTests=true
ng generate component  alimentos-list  --skipTests=true
cd ..
mkdir alimentosrestricao
cd alimentosrestricao
ng generate module alimentosrestricao --routing
ng generate component  alimentosrestricao-list  --skipTests=true
ng generate component  alimentosrestricao-forms  --skipTests=true

cd ..
mkdir dieta
cd dieta
ng generate module dieta --routing
ng generate component  dieta-list  --skipTests=true
ng generate component  dieta-forms  --skipTests=true

cd ..
mkdir enderecos
cd enderecos
ng generate module enderecos --routing
ng generate component  enderecos-list  --skipTests=true
ng generate component  enderecos-forms  --skipTests=true


cd ..
mkdir equipamentos
cd equipamentos
ng generate module equipamentos --routing
ng generate component  equipamentos-list --skipTests=true
ng generate component  equipamentos-forms --skipTests=true

cd ..
mkdir fisiologia
ng generate module fisiologia --routing
cd fisiologia
ng generate module fisiologia --routing
ng generate component  fisiologia-list --skipTests=
ng generate component  fisiologia-forms --skipTests=true

cd ..
mkdir pessoa
cd pessoa
ng generate module pessoa --routing
ng generate component  pessoa-list --skipTests=true
ng generate component  pessoa-forms --skipTests=true

cd ..
mkdir refeicoes
cd refeicoes
ng generate module refeicoes --routing
ng generate component  refeicoes-list --skipTests=true
ng generate component  refeicoes-forms --skipTests=true

cd ..
mkdir restricaoalimentar
cd restricaoalimentar
ng generate module restricaoalimentar --routing
ng generate component  restricaoalimentar-list --skipTests=true
ng generate component  restricaoalimentar-forms --skipTests=true

cd ..
mkdir treinos
cd treinos
ng generate module treinos --routing
ng generate component  treinos-list --skipTests=true
ng generate component  treinos-forms --skipTests=true


ng --install --routing --package-manager npm --commit --view-encapsulation Emulated

ng s --open  --ssl=true
