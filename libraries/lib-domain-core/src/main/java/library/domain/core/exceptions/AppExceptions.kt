package library.domain.core.exceptions

class RemoteApiExceptions : Exception("Não foi possível executar esta ação, verifique sua internet e tente novamente.")

class UnknownExceptions : Exception("Não foi possível executar esta ação, tente novamente, se persistir o problema, por favor abra uma issue no repositório.")